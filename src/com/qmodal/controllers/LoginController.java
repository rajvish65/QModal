/**
 * 
 */
package com.qmodal.controllers;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qmodal.pojos.News;
import com.qmodal.service.interfaces.IQModalService;
import com.qmodal.util.QModalJSPMappings;
import com.qmodal.util.QModalLoggerConstants;
import com.qmodal.util.QModalURLMappings;

/**
 * Contains all the methods related to User Login
 * @author RVishwakarma
 *
 */
@Controller
public class LoginController
{
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	IQModalService qmodalService;
	
	/**
	 * Displays the Login Page on Successful Validation of Credentials
	 * @author RVishwakarma
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return LoginPage
	 */
	@RequestMapping(value=QModalURLMappings.LOGIN_PAGE,method=RequestMethod.GET)
	public String showLoginPage(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)
	{	
		logger.info(QModalLoggerConstants.LOGIN_PAGE);
		return QModalJSPMappings.LOGIN_PAGE;
	}
	
	/**
	 * Displays the Login Failed Page on Invalid Credentials
	 * @author RVishwakarma
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return LoginPage
	 */
	@RequestMapping(value=QModalURLMappings.LOGIN_FAILED_PAGE,method=RequestMethod.GET)
	public String showLoginFailedPage(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)
	{
		logger.info(QModalLoggerConstants.LOGIN_FAILED);
		return QModalJSPMappings.LOGIN_PAGE;
	}
	
	/**
	 * Displays the  Main Page on Proper Credentials
	 * @author RVishwakarma
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return LoginPage
	 */
	@RequestMapping(value=QModalURLMappings.MAIN_PAGE,method=RequestMethod.GET)
	public String showDashboardPage(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,Model model)
	{	
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmailId = authentication.getName();
		
		List<News> modals = new ArrayList<News>();
		try {
			modals = qmodalService.getModals();
		}catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		}
		
		model.addAttribute("modals",modals);
		
		logger.info(QModalLoggerConstants.MAIN_PAGE);
		return QModalJSPMappings.MAIN_PAGE;
	}
	
	
	/*@RequestMapping(value=QModalURLMappings.MAIN_PAGE,method=RequestMethod.POST)
	public String postData(@ModelAttribute("successfullyPostedQuestion") String successfullyPostedQuestion,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,Model model)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmailId = authentication.getName();

		
		logger.info(QModalLoggerConstants.MAIN_PAGE);
		return QModalJSPMappings.MAIN_PAGE;
	}*/
}
