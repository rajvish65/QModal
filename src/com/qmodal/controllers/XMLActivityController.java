package com.qmodal.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qmodal.pojos.News;
import com.qmodal.service.interfaces.IQModalService;
import com.qmodal.util.QModalCommonUtility;
import com.qmodal.util.QModalJSPMappings;
import com.qmodal.util.QModalLoggerConstants;
import com.qmodal.util.QModalMessageConstants;
import com.qmodal.util.QModalURLMappings;

@Controller
public class XMLActivityController {
	
	@Autowired
	IQModalService qmodalService;
	
	private final Logger logger = Logger.getLogger(getClass());
	
	@RequestMapping(value= QModalURLMappings.ACTIVITY_PAGE,method=RequestMethod.GET)
	public String LoadActivityPage(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,Model model){
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmailId = authentication.getName();
		
		List<News> modals = new ArrayList<News>();
		try {
			modals = qmodalService.getModals();
		}catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		}
		
		model.addAttribute("modals",modals);
		
		logger.info(QModalLoggerConstants.ACTIVITY_PAGE);
		return QModalJSPMappings.ACTIVITY_PAGE;
	}
	
	@RequestMapping(value=QModalURLMappings.CREATE_MODAL_PAGE,method=RequestMethod.GET)
	public String createModal(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,Model model){
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmailId = authentication.getName();
		
		List<News> modals = new ArrayList<News>();
		try {
			modals = qmodalService.getModals();
		}catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		}
		
		model.addAttribute("news",new News());
		model.addAttribute("modals",modals);
		
		logger.info(QModalLoggerConstants.CREATE_MODAL_PAGE);
		return QModalJSPMappings.CREATE_MODAL_PAGE;
	}
	
	@RequestMapping(value=QModalURLMappings.UPDATE_MODAL_PAGE,method=RequestMethod.GET)
	public String viewModal(@RequestParam(value="type",required=false) String type,Model model)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmailId = authentication.getName();
		
		List<News> modals = new ArrayList<News>();
		News news = new News();
		
		if( null != type && type.length() > 0)
		{
			try {
				modals = qmodalService.getModals();
				news = qmodalService.getModalNews(type);
			}catch(Exception ex)
			{
				logger.error(QModalMessageConstants.EMPTY_RESULT_SET);
				logger.error(ex.getLocalizedMessage());
			}
		}
	
		model.addAttribute("modals",modals);
		model.addAttribute("news", news);
		logger.info(QModalLoggerConstants.UPDATE_MODAL_PAGE);
		return QModalJSPMappings.UPDATE_MODAL_PAGE;
	}
	
	
	
	@RequestMapping(value = QModalURLMappings.CREATE_MODAL_PAGE,method=RequestMethod.POST)
	public String createModal(@ModelAttribute("news") @Valid News news,BindingResult result,Model model,RedirectAttributes redirectAttributes)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmailId = authentication.getName();
		
		List<News> modals = new ArrayList<News>();
			try {

				if (result.hasErrors()) {
					modals = qmodalService.getModals();
					model.addAttribute("modals",modals);
					return QModalJSPMappings.CREATE_MODAL_PAGE;
				} else {
					qmodalService.createModal(news);
				}
			}catch(Exception ex)
			{
				logger.error(QModalMessageConstants.EMPTY_RESULT_SET);
				logger.error(ex.getLocalizedMessage());
			}
	
		logger.info(QModalLoggerConstants.MODAL_CREATED);
		redirectAttributes.addFlashAttribute("successfullyModalCreated","Modal Created Successfully");
		return QModalURLMappings.REDIRECT_SUCCESS_MODAL_CREATED;
	}
	
	@RequestMapping(value=QModalURLMappings.UPDATE_MODAL_PAGE,method=RequestMethod.POST,params="update")
	public String updateModal(@ModelAttribute("news") News news,RedirectAttributes redirectAttributes)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmailId = authentication.getName();
		
			try {
                qmodalService.updateModal(news);
			}catch(Exception ex)
			{
				logger.error(QModalMessageConstants.EMPTY_RESULT_SET);
				logger.error(ex.getLocalizedMessage());
			}
	
		logger.info(QModalLoggerConstants.MODAL_UPDATED);
		redirectAttributes.addFlashAttribute("successfullyModalUpdated","Modal Updated Successfully");
		return QModalURLMappings.REDIRECT_SUCCESS_MODAL_UPDATED+news.getType();
	}
	
	@RequestMapping(value=QModalURLMappings.UPDATE_MODAL_PAGE,method=RequestMethod.POST,params="delete")
	public String deleteModal(@RequestParam(value="type",required=false) String type,RedirectAttributes redirectAttributes)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmailId = authentication.getName();
		
			try {
                qmodalService.deleteModal(type);
			}catch(Exception ex)
			{
				logger.error(QModalMessageConstants.EMPTY_RESULT_SET);
				logger.error(ex.getLocalizedMessage());
			}
	
		logger.info(QModalLoggerConstants.MODAL_DELETED);
		redirectAttributes.addFlashAttribute("successfullyModalDeleted","Modal Deleted Successfully");
		return QModalURLMappings.REDIRECT_SUCCESS_MODAL_DELETED;
	}
	
	@RequestMapping(value=QModalURLMappings.DELETE_MODAL_PAGE,method=RequestMethod.GET)
	public String deleteModals(@RequestParam(value="types",required=false) String types,RedirectAttributes redirectAttributes)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmailId = authentication.getName();
		
			try {
                qmodalService.deleteModals(types);
			}catch(Exception ex)
			{
				logger.error(QModalMessageConstants.EMPTY_RESULT_SET);
				logger.error(ex.getLocalizedMessage());
			}
	
		logger.info(QModalLoggerConstants.MODAL_DELETED);
		redirectAttributes.addFlashAttribute("successfullyModalDeleted","Modal Deleted Successfully");
		return QModalURLMappings.REDIRECT_SUCCESS_MODAL_DELETED;
	}
	
	
	@RequestMapping(value=QModalURLMappings.NEWS_IMAGE)
	String getNewsImage(@RequestParam(value="type",required=false) String type,HttpServletRequest request,HttpServletResponse response)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String loggedInUserEmail = authentication.getName();
		
		News news = qmodalService.getNewsImage(type);
		String imageName = news.getImageName();
		byte[] image = null;
		
		if(null != imageName && imageName.length() > 0)
		{
			image =  news.getNewsImageData();
			imageName = news.getImageName();
		}
		
		   try
		   {
		    String imageFormat=null;
		    //Code to Accommodate All Image Formats(namely PNG,JPEG,BMP)
		    
		    if(imageName != null && !imageName.equals(""))
		    {
		     //To check for file format and render accordingly
		     String fileExtension = QModalCommonUtility.getFileExtension(imageName);
		     imageFormat="image/"+fileExtension;
		     response.setContentType(imageFormat);
		     response.setContentLength(image.length);
		    }
		     //Setting response headers
		      response.setHeader("Content-Disposition", "inline; filename=\"" + imageName + "\"");
		      response.getOutputStream().write(image);
		      response.getOutputStream().flush();
		      response.getOutputStream().close();
		   
		   }
		   catch(Exception ex)
		   {
			   logger.error(ex.getLocalizedMessage());
		   }
		return null;
	}
	
	@RequestMapping(value = QModalURLMappings.NEWS_IMAGE_UPLOAD,method=RequestMethod.POST,params="uploadImage")
	public String newImageUpload(@ModelAttribute("news") @Valid News news,BindingResult result,Model model,RedirectAttributes redirectAttributes)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmailId = authentication.getName();
		
		try {
            qmodalService.uploadNewsImage(news);
		}catch(Exception ex)
		{
			logger.error(QModalMessageConstants.EMPTY_RESULT_SET);
			logger.error(ex.getLocalizedMessage());
		}
	
		logger.info(QModalLoggerConstants.MODAL_CREATED);
		redirectAttributes.addFlashAttribute("successfullyImageUploaded","Image Uploaded Successfully");
		return QModalURLMappings.REDIRECT_IMAGE_UPLOAD+news.getType();
	}
	
	@RequestMapping(value = QModalURLMappings.NEWS_IMAGE_UPLOAD,method=RequestMethod.POST,params="removeImage")
	public String removeImageUploaded(@ModelAttribute("news") @Valid News news,BindingResult result,Model model,RedirectAttributes redirectAttributes)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmailId = authentication.getName();
		
		try {
            qmodalService.removeNewsImage(news);
		}catch(Exception ex)
		{
			logger.error(QModalMessageConstants.EMPTY_RESULT_SET);
			logger.error(ex.getLocalizedMessage());
		}
	
		logger.info(QModalLoggerConstants.MODAL_CREATED);
		redirectAttributes.addFlashAttribute("successfullyImageRemoved","Image Removed Successfully");
		return QModalURLMappings.REDIRECT_IMAGE_REMOVED+news.getType();
	}
}
