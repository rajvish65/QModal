/**
 * 
 */
package com.qmodal.service.interfaces;

import java.util.List;

import com.qmodal.pojos.News;


/**
 * @author RVishwakarma
 *
 */
public interface IQModalService {

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
	 * @return Void
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

	
}
