package com.qmodal.pojos;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class News {
	
	private long newsId;
	
	@NotEmpty(message = "Please provide Modal Type.")
	private String type;
	
	private String header;
	private String content;
	private String footer;
	private String imageName;
	private MultipartFile newsImage;
	private byte[] newsImageData;
	private Boolean newsImageUploaded;

	
	public long getNewsId() {
		return newsId;
	}
	public void setNewId(long newId) {
		this.newsId = newId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type; 
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFooter() {
		return footer;
	}
	public void setFooter(String footer) {
		this.footer = footer;
	}
	public MultipartFile getNewsImage() {
		return newsImage;
	}
	public void setNewsImage(MultipartFile newsImage) {
		this.newsImage = newsImage;
	}
	public Boolean getNewsImageUploaded() {
		return newsImageUploaded;
	}
	public void setNewsImageUploaded(Boolean newsImageUploaded) {
		this.newsImageUploaded = newsImageUploaded;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public byte[] getNewsImageData() {
		return newsImageData;
	}
	public void setNewsImageData(byte[] newsImageData) {
		this.newsImageData = newsImageData;
	}
	
	
	
	/*@Override
	public String toString() {
		return "{'type' :" + type + ", header=" + header + ", content="
				+ content + ", footer=" + footer + "}";
		
		return "{'type' : '"+type+"', 'header' : '"+header+"', 'content' : '"+content+"', 'footer' :'"+footer+"'}";
		
	}*/
	
	

}
