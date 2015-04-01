/**
 * 
 */
package com.qmodal.service;

import java.util.List;

import com.qmodal.dao.interfaces.IQModalDao;
import com.qmodal.pojos.News;
import com.qmodal.service.interfaces.IQModalService;
import com.sun.org.apache.bcel.internal.generic.RETURN;

/**
 * @author RVishwakarma
 *
 */
public class QModalServiceManager implements IQModalService {

	IQModalDao qmodalDao;

	public IQModalDao getQmodalDao() {
		return qmodalDao;
	}

	public void setQmodalDao(IQModalDao qmodalDao) {
		this.qmodalDao = qmodalDao;
	}
	
	

	/**
	 * @author RVishwakarma
	 * @param type
	 * @return String
	 */
	@Override
	public String getNews(String type) {
		return qmodalDao.getNews(type);
	}

	/**
	 * @author rvishwakarma
	 * @return List<News>
	 */
	@Override
	public List<News> getModals() {
		return qmodalDao.getModals();
	}

	/**
	 * @author rvishwakarma
	 * @return News
	 */
	@Override
	public News getModalNews(String type) {
		return qmodalDao.getModalNews(type);
	}

	/**
	 * @author rvishwakarma
	 * @return Void
	 */
	@Override
	public void createModal(News news) {
		qmodalDao.createModal(news);

	}

	/**
	 * @author rvishwakarma
	 * return void
	 */
	@Override
	public void updateModal(News news) {
		qmodalDao.updateModal(news);
	}

	/**
	 * @author rvishwakarma
	 * @param type
	 * @return void
	 */
	@Override
	public void deleteModal(String type) {
		qmodalDao.deleteModal(type);
	}

	/**
	 * @author rvishwakarma
	 * @param types
	 */
	@Override
	public void deleteModals(String types) {
		qmodalDao.deleteModals(types);
	}

	/**
	 * @author rvishwakarma
	 * @param type
	 * @return News
	 */
	@Override
	public News getNewsImage(String type) {
		return qmodalDao.getNewsImage(type);
	}

	/**
	 * @author rvishwakarma
	 * @param news
	 */
	@Override
	public void uploadNewsImage(News news) {
        qmodalDao.uploadNewsImage(news);             
	}

	
	
}
