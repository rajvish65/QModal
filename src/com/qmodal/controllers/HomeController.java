/**
 * 
 */
package com.qmodal.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qmodal.service.interfaces.IQModalService;
import com.qmodal.util.QModalJSPMappings;
import com.qmodal.util.QModalLoggerConstants;
import com.qmodal.util.QModalURLMappings;


/**
 * @author RVishwakarma
 *
 */
@Controller
public class HomeController {
	
	@Autowired
	IQModalService qmodalService;
	
	private final Logger logger = Logger.getLogger(getClass());
	
	/**
	 * Displays the Home Page
	 * @author RVishwakarma
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return LoginPage
	 */
	@RequestMapping(value=QModalURLMappings.MODAL_PAGE,method=RequestMethod.GET)
	public String showHomePage(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)
	{
		logger.info(QModalLoggerConstants.MODAL_PAGE);
		return QModalJSPMappings.MODAL_PAGE;
	}

	@RequestMapping(value=QModalURLMappings.MODAL_JSON,method=RequestMethod.GET)
	@ResponseBody
	public String showModalPage(@RequestParam("type") String type)
	{
		String news= null;
        
		try {
			news = qmodalService.getNews(type);
		}catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		}
		
		logger.info(QModalLoggerConstants.MODAL_JSON);
		return news;
	}
}
