/**
 * 
 */
package com.qmodal.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.cassandra.core.RowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import com.datastax.driver.core.Row;
import com.datastax.driver.core.exceptions.DriverException;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.qmodal.dao.interfaces.IQModalDao;
import com.qmodal.pojos.News;
import com.qmodal.util.QModalCommonUtility;
import com.qmodal.util.QModalMessageConstants;

/**
 * Contains all the methods of DB insertion
 * @author RVishwakarma
 */
public class QModalJdbcDao extends JdbcDaoSupport implements IQModalDao {

	private final Logger logger = Logger.getLogger(getClass());

	/**
	 * @author RVishwakarma
	 * @param type
	 * @return String
	 */
	@Override
	public String getNews(String type) {

        News news = new News();
        News newsAll = new News();
		
        String defaultNews ="All";
        
        final String SELECT_NEWS = "Select newsId,type,header,content,footer,newsImageUploaded,newsImageName,newsImage from news where type=?";
		try {
			
			String newsCountStr = "SELECT COUNT(*) FROM news where type=?";
			 
			int newExistCount = getJdbcTemplate().queryForInt(newsCountStr, new Object[] { type });
			
			if(newExistCount > 0)
			{
				news = getJdbcTemplate().queryForObject(SELECT_NEWS, new Object[] { type } ,new ModalNewsMapper());
			}
			else
			{
				newsAll = getJdbcTemplate().queryForObject(SELECT_NEWS, new Object[] { defaultNews } ,new ModalNewsMapper());
			}
		} catch (NullPointerException emptyEx) {
			logger.error(QModalMessageConstants.EMPTY_RESULT_SET);
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		}

		
		String newsStr = "";
		ObjectMapper objMapper = new ObjectMapper();
		try {
			if(null != news.getType())
				newsStr = objMapper.writeValueAsString(news);
			else 
				if(null != newsAll.getType())
				newsStr = objMapper.writeValueAsString(newsAll);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return newsStr;
	}

	/**
	 * @author rvishwakarma
	 * @return List<News>
	 */
	@Override
	public List<News> getModals() {
		
		List<News> newsList = new ArrayList<News>();
      
		final String SELECT_MODALS = "Select newsId,type,header,content,footer,newsImageUploaded,newsImageName from news";
		
		try {
			newsList = getJdbcTemplate().query(SELECT_MODALS,new NewsMapper());
		} catch (NullPointerException emptyEx) {
			logger.error(QModalMessageConstants.EMPTY_RESULT_SET);
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		}
		return newsList;
	}

	@Override
	public News getModalNews(String type) {

		News news = new News();
		
        final String SELECT_MODAL_NEWS = "Select newsId,type,header,content,footer,newsImageUploaded,newsImageName from news where type=?";
		try {
			news = getJdbcTemplate().queryForObject(SELECT_MODAL_NEWS, new Object[] { type } ,new NewsMapper());
		} catch (NullPointerException emptyEx) {
			logger.error(QModalMessageConstants.EMPTY_RESULT_SET);
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		}
		return news;
	}

	
	@Override
	public void createModal(News news) {
		
		final String INSERT_NEWS_WITH_IMAGE = "insert into news (type,header,content,footer,newsImage,newsImageUploaded,newsImageName,createdBy,createdDate) values (?,?,?,?,?,?,?,?,?)";
		final String INSERT_NEWS_WITHOUT_IMAGE = "insert into news (type,header,content,footer,newsImageUploaded,createdBy,createdDate) values (?,?,?,?,?,?,?)";
		try {
			if(news.getNewsImage().getSize() > 0)
    		{
			String imageName = news.getNewsImage().getOriginalFilename();
			byte [] originalImage = news.getNewsImage().getBytes();
			
			byte[] compressedImage = null;
			if(QModalCommonUtility.getFileExtension(news.getNewsImage().getOriginalFilename()).equalsIgnoreCase("jpg") 
					|| QModalCommonUtility.getFileExtension(news.getNewsImage().getOriginalFilename()).equalsIgnoreCase("jpeg") )
				compressedImage = QModalCommonUtility.returnCompressedImage(originalImage);
			else
				compressedImage = originalImage;
			
				getJdbcTemplate().update(INSERT_NEWS_WITH_IMAGE,new Object[]{news.getType(),news.getHeader(),news.getContent(),news.getFooter(),compressedImage,true,imageName,null,new Date()});
    		}
			else
			{	
				getJdbcTemplate().update(INSERT_NEWS_WITHOUT_IMAGE,new Object[]{news.getType(),news.getHeader(),news.getContent(),news.getFooter(),false,null,new Date()});
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author rvishwakarma
	 * @param news
	 * @return void
	 */
	@Override
	public void updateModal(News news) {
		
        final String UPDATE_NEWS = "Update news set header=?,content=?,footer=?,updatedBy=?,updatedDate=? from news where type=?";
		try {
			getJdbcTemplate().update(UPDATE_NEWS, new Object[] { news.getHeader(),news.getContent(),news.getFooter(),null,new Date(),news.getType() });
		} catch (NullPointerException emptyEx) {
			logger.error(QModalMessageConstants.EMPTY_RESULT_SET);
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		}
	}

	@Override
	public void deleteModal(String type) {
		
		final String DELETE_NEWS = "Delete from news where type=?";
		try {
			getJdbcTemplate().update(DELETE_NEWS, new Object[] { type } );
		} catch (NullPointerException emptyEx) {
			logger.error(QModalMessageConstants.EMPTY_RESULT_SET);
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		}
	}

	/**
	 * @author rvishwakarma
	 * @param types
	 */
	@Override
	public void deleteModals(String types) {
		
		final String DELETE_NEWS = "Delete from news where type=?";
		try {
			String[] typeArray;
			typeArray  = types.split(",");
		
			for (int index = 0; index < typeArray.length; index++) {
				String type = typeArray[index].toString();
				getJdbcTemplate().update(DELETE_NEWS, new Object[] { type } );
			}
		} catch (NullPointerException emptyEx) {
			logger.error(QModalMessageConstants.EMPTY_RESULT_SET);
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		}	

	}

	/**
	 * @author rvishwakarma
	 * @param type
	 * @return News
	 */
	@Override
	public News getNewsImage(String type) {
		News news = new News();
		
        final String SELECT_NEWS_IMAGE = "Select newsImage,newsImageName from news where type=?";
		try {
			news = getJdbcTemplate().queryForObject(SELECT_NEWS_IMAGE, new Object[] { type } ,new NewsImageMapper());
		} catch (NullPointerException emptyEx) {
			logger.error(QModalMessageConstants.EMPTY_RESULT_SET);
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		}	
		return news== null ? new News() : news;
	}

	/**
	 * @author rvishwakarma
	 * @param news
	 */
	@Override
	public void uploadNewsImage(News news) {
		
		final String UPDATE_NEWS_IMAGE = "Update news set newsImage=?,newsImageName=?,newsImageUploaded=?,updatedBy=?,updatedDate=? from news where type=?";
		try {
			if(news.getNewsImage() != null)
    		{
			String imageName = news.getNewsImage().getOriginalFilename();
			byte [] originalImage = news.getNewsImage().getBytes();
			
			byte[] compressedImage = null;
			if(QModalCommonUtility.getFileExtension(news.getNewsImage().getOriginalFilename()).equalsIgnoreCase("jpg") 
					|| QModalCommonUtility.getFileExtension(news.getNewsImage().getOriginalFilename()).equalsIgnoreCase("jpeg") )
				compressedImage = QModalCommonUtility.returnCompressedImage(originalImage);
			else
				compressedImage = originalImage;
			
			getJdbcTemplate().update(UPDATE_NEWS_IMAGE, new Object[] { compressedImage,imageName,true,null,new Date(),news.getType() });
    		}
		} catch (NullPointerException emptyEx) {
			logger.error(QModalMessageConstants.EMPTY_RESULT_SET);
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		}
	}
	
	private class NewsMapper implements ParameterizedRowMapper<News>
	{
		@Override
		public News mapRow(ResultSet rs, int arg1) throws SQLException {
			
			News news = new News();
			news.setNewId(rs.getLong("newsId"));
			news.setType(rs.getString("type"));
			news.setHeader(rs.getString("header"));
			news.setContent(rs.getString("content"));
			news.setFooter(rs.getString("footer"));
			news.setImageName(rs.getString("newsImageName"));
			news.setNewsImageUploaded(rs.getBoolean("newsImageUploaded"));

			return news;
		}
	}
	
	private class ModalNewsMapper implements ParameterizedRowMapper<News>
	{
		@Override
		public News mapRow(ResultSet rs, int arg1) throws SQLException {
			
			News news = new News();
			news.setNewId(rs.getLong("newsId"));
			news.setType(rs.getString("type"));
			news.setHeader(rs.getString("header"));
			news.setContent(rs.getString("content"));
			news.setFooter(rs.getString("footer"));
			news.setImageName(rs.getString("newsImageName"));
			news.setNewsImageUploaded(rs.getBoolean("newsImageUploaded"));
			news.setNewsImageData(rs.getBytes("newsImage"));
			return news;
		}
	}
	
	private class NewsImageMapper implements ParameterizedRowMapper<News>
	{
		@Override
		public News mapRow(ResultSet rs, int arg1) throws SQLException  {
			News news = new News();
			
			if(rs.getBytes("newsImage") != null){
				news.setImageName("newsImageName");
				news.setNewsImageData(rs.getBytes("newsImage"));
			}
			return news;
		}
		
	}


	/**
	 * @author rvishwakarma
	 * @param news
	 */
	@Override
	public void removeNewsImage(News news) {
		final String REMOVE_NEWS_IMAGE = "Update news set newsImage=?,newsImageName=?,newsImageUploaded=?,updatedBy=?,updatedDate=? from news where type=?";
		try {
			getJdbcTemplate().update(REMOVE_NEWS_IMAGE, new Object[] { null,null,false,null,new Date(),news.getType() });
		} catch (NullPointerException emptyEx) {
			logger.error(QModalMessageConstants.EMPTY_RESULT_SET);
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getLocalizedMessage());
		}
	}
}
