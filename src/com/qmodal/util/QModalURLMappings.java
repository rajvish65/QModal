/**
 * 
 */
package com.qmodal.util;

/**
 * Contains all the URL Mappings
 * @author RVishwakarma
 */
public class QModalURLMappings {
	
	public static final String LOGIN_PAGE = "login.htm"; //LoginController.java
	public static final String LOGIN_FAILED_PAGE = "/loginfailed.htm"; //LoginController.java
	public static final String DASHBOARD_PAGE = "/userdashboard.htm"; //LoginController.java
	public static final String MAIN_PAGE = "/main.htm"; //LoginController.java
	
	public static final String MODAL_PAGE = "modal.htm"; //HomeController.java
	public static final String MODAL_JSON = "modal.json"; //HomeController.java

	public static final String ACTIVITY_PAGE = "activity.htm"; //XMLActivityController.java
	public static final String CREATE_MODAL_PAGE = "create_modal.htm"; //XMLActivityController.java
	public static final String DELETE_MODAL_PAGE = "delete_modal.htm"; //XMLActivityController.java
	public static final String UPDATE_MODAL_PAGE = "update_modal.htm"; //XMLActivityController.java
	public static final String REDIRECT_SUCCESS_MODAL_CREATED = "redirect:"+MAIN_PAGE;  //XMLActivityController.java
	public static final String REDIRECT_SUCCESS_MODAL_UPDATED = "redirect:"+UPDATE_MODAL_PAGE+"?type="; //XMLActivityController.java
	public static final String REDIRECT_SUCCESS_MODAL_DELETED = "redirect:"+MAIN_PAGE;  //XMLActivityController.java
	public static final String REDIRECT_CREATE_MODAL = "redirect:"+CREATE_MODAL_PAGE;  //XMLActivityController.java
	public static final String NEWS_IMAGE = "news_image.htm";  //XMLActivityController.java
	public static final String NEWS_IMAGE_UPLOAD= "news_image_upload.htm";  //XMLActivityController.java
	public static final String REDIRECT_IMAGE_UPLOAD = "redirect:"+UPDATE_MODAL_PAGE+"?type=";  //XMLActivityController.java
	public static final String REDIRECT_IMAGE_REMOVED = "redirect:"+UPDATE_MODAL_PAGE+"?type=";  //XMLActivityController.java
}
