/**
 * 
 */
package com.qmodal.dao.interfaces;

import java.util.List;

import com.qmodal.pojos.News;

/**
 * @author RVishwakarma
 *
 */
public interface IQModalDao {

	/**
	 * @author RVishwakarma
	 * @param type
	 * @return String
	 */
	String getNews(String type);

	/**
	 * @author rvishwakarma
	 * @return List<News>
	 */
	List<News> getModals();

	/**
	 * @author rvishwakarma
	 * @param type
	 * @return News
	 */
	News getModalNews(String type);

	/**
	 * @author rvishwakarma
	 * @param news
	 */
	void createModal(News news);

	/**
	 * @author rvishwakarma
	 * @param news
	 */
	void updateModal(News news);

	/**
	 * @author rvishwakarma
	 * @param type
	 */
	void deleteModal(String type);

	/**
	 * @author rvishwakarma
	 * @param types
	 */
	void deleteModals(String types);

	/**
	 * @author rvishwakarma
	 * @param type
	 * @return News
	 */
	News getNewsImage(String type);

	/**
	 * @author rvishwakarma
	 * @param news
	 */
	void uploadNewsImage(News news);

	/**
	 * @author rvishwakarma
	 * @param news
	 */
	void removeNewsImage(News news);


}
