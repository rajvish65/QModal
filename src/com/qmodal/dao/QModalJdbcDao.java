/**
 * 
 */
package com.qmodal.dao;

import java.io.File;
import java.util.ArrayList;
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
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qmodal.dao.interfaces.IQModalDao;
import com.qmodal.pojos.News;
import com.qmodal.util.QModalCommonUtility;

/**
 * Contains all the methods of DB insertion
 * @author RVishwakarma
 */
public class QModalJdbcDao extends JdbcDaoSupport implements IQModalDao {

	private final Logger logger = Logger.getLogger(getClass());

	private static final String XML_LOCATION = "C:/modal/news.xml";

	/**
	 * @author RVishwakarma
	 * @param type
	 * @return String
	 */
	@Override
	public String getNews(String type) {

        News news = new News();
        News newsAll = new News();
		
		try {
			File fXmlFile = new File(XML_LOCATION);
			//InputStream is = getClass().getResourceAsStream("/resources/news.xml");//from resource folder
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			Document doc = dBuilder.parse(fXmlFile);
			//Document doc = dBuilder.parse(is);//from resource folder 
			doc.getDocumentElement().normalize();
		 
			NodeList nList = doc.getElementsByTagName("news");
		 
			for (int temp = 0; temp < nList.getLength(); temp++) {
		 
				Node nNode = nList.item(temp);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					if(eElement.getAttribute("type").equals(type))
					{
						news.setType(eElement.getAttribute("type"));
						news.setHeader(eElement.getElementsByTagName("header").item(0).getTextContent());
						news.setContent(eElement.getElementsByTagName("content").item(0).getTextContent());
						news.setFooter(eElement.getElementsByTagName("footer").item(0).getTextContent());
						news.setImageName(eElement.getElementsByTagName("newsImageName").item(0).getTextContent());
						news.setNewsImageData(Base64.decodeBase64(eElement.getElementsByTagName("newsImage").item(0).getTextContent()));
						news.setNewsImageUploaded(Boolean.valueOf(eElement.getElementsByTagName("newsImageUploaded").item(0).getTextContent()));
					}
					
					if(eElement.getAttribute("type").equals("all"))
					{
						newsAll.setType(eElement.getAttribute("type"));
						newsAll.setHeader(eElement.getElementsByTagName("header").item(0).getTextContent());
						newsAll.setContent(eElement.getElementsByTagName("content").item(0).getTextContent());
						newsAll.setFooter(eElement.getElementsByTagName("footer").item(0).getTextContent());
						newsAll.setImageName(eElement.getElementsByTagName("newsImageName").item(0).getTextContent());
						newsAll.setNewsImageData(Base64.decodeBase64(eElement.getElementsByTagName("newsImage").item(0).getTextContent()));
						newsAll.setNewsImageUploaded(Boolean.valueOf(eElement.getElementsByTagName("newsImageUploaded").item(0).getTextContent()));
					}	
				}
			  }
		    } catch (Exception e) {
		    		e.printStackTrace();
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
      
		try {
			File fXmlFile = new File(XML_LOCATION);
			//InputStream is = getClass().getResourceAsStream("/resources/news.xml");//from resource folder
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			Document doc = dBuilder.parse(fXmlFile);
			//Document doc = dBuilder.parse(is);//from resource folder 
			doc.getDocumentElement().normalize();
		 
			NodeList nList = doc.getElementsByTagName("news");
		 
			for (int temp = 0; temp < nList.getLength(); temp++) {
		 
				Node nNode = nList.item(temp);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
					News news = new News();
					news.setType(eElement.getAttribute("type"));
					news.setHeader(eElement.getElementsByTagName("header").item(0).getTextContent());
					news.setContent(eElement.getElementsByTagName("content").item(0).getTextContent());
					news.setFooter(eElement.getElementsByTagName("footer").item(0).getTextContent());
					
					newsList.add(news);	
				}
			  }
		    } catch (Exception e) {
		    		e.printStackTrace();
		    }
		
		return newsList;
	}

	@Override
	public News getModalNews(String type) {

		News news = new News();
		
		try {
			File fXmlFile = new File(XML_LOCATION);
			//InputStream is = getClass().getResourceAsStream("/resources/news.xml");//from resource folder
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			Document doc = dBuilder.parse(fXmlFile);
			//Document doc = dBuilder.parse(is);//from resource folder 
			doc.getDocumentElement().normalize();
		 
			NodeList nList = doc.getElementsByTagName("news");
		 
			for (int temp = 0; temp < nList.getLength(); temp++) {
		 
				Node nNode = nList.item(temp);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					if(eElement.getAttribute("type").equals(type))
					{
						news.setType(eElement.getAttribute("type"));
						news.setHeader(eElement.getElementsByTagName("header").item(0).getTextContent());
						news.setContent(eElement.getElementsByTagName("content").item(0).getTextContent());
						news.setFooter(eElement.getElementsByTagName("footer").item(0).getTextContent());
						news.setImageName(eElement.getElementsByTagName("newsImageName").item(0).getTextContent());
						news.setNewsImageData(Base64.decodeBase64(eElement.getElementsByTagName("newsImage").item(0).getTextContent()));
						news.setNewsImageUploaded(Boolean.valueOf(eElement.getElementsByTagName("newsImageUploaded").item(0).getTextContent()));
					
						
					}	
				}
			  }
		    } catch (Exception e) {
		    		e.printStackTrace();
		    }
	
		return news;
	}

	
	@Override
	public void createModal(News news) {
		
        try {
        	
        	File xmlFile = new File(XML_LOCATION);
        	
        	DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);

            Element documentElement = document.getDocumentElement();
            
            Element headerElement = document.createElement("header");
            Text headerValue = document.createTextNode(news.getHeader().toString());
            headerElement.setTextContent(news.getHeader().toString());
            
            Element contentElement = document.createElement("content");
            Text contentvalue = document.createTextNode(news.getContent().toString());
            contentElement.setTextContent(news.getContent().toString());
            
            Element footerElement = document.createElement("footer");
            Text contentValue = document.createTextNode(news.getFooter().toString());
            footerElement.setTextContent(news.getFooter().toString());
            
            Element imageNameElement;
            Element imageElement;
            Element isImageUploadedElement;
            
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
				
				imageNameElement = document.createElement("newsImageName");
	            Text imageNameValue = document.createTextNode(imageName);
	            imageNameElement.setTextContent(imageName);	
				
				imageElement = document.createElement("newsImage");
						
	            Text imageValue = document.createTextNode(new String(compressedImage));
	            imageElement.setTextContent(new String(compressedImage));
	            
	            isImageUploadedElement = document.createElement("newsImageUploaded");
	            Text isImageUploadedValue = document.createTextNode(String.valueOf(true));
	            isImageUploadedElement.setTextContent(String.valueOf(true));	
    		}
            else
            {
            	imageNameElement = document.createElement("newsImageName");
	            Text imageNameValue = document.createTextNode(String.valueOf(""));
	            imageNameElement.setTextContent(String.valueOf(""));	
	            
            	imageElement = document.createElement("newsImage");
                Text imageValue = document.createTextNode(String.valueOf(""));
                imageElement.setTextContent(String.valueOf(""));
                
                isImageUploadedElement = document.createElement("newsImageUploaded");
                Text isImageUploadedValue = document.createTextNode(String.valueOf(false));
                isImageUploadedElement.setTextContent(String.valueOf(false));
            }
            
            //Create a Node element
            Element nodeElement = document.createElement("news");
            nodeElement.setAttribute("type", news.getType().toString());

            //append textNode to Node element;
            nodeElement.appendChild(headerElement);
            nodeElement.appendChild(contentElement);
            nodeElement.appendChild(footerElement);
            nodeElement.appendChild(imageNameElement);
            nodeElement.appendChild(imageElement);
            nodeElement.appendChild(isImageUploadedElement);
            //append Node to rootNode element
            documentElement.appendChild(nodeElement);
            document.replaceChild(documentElement, documentElement);
            
            Transformer tFormer =TransformerFactory.newInstance().newTransformer();
            tFormer.setOutputProperty(OutputKeys.METHOD, "xml");

            Source source = new DOMSource(document);
            Result result = new StreamResult(xmlFile);
			tFormer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
		}catch (Exception e) {
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
		try {
        	File xmlFile = new File(XML_LOCATION);
        	
        	DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);

            document.getDocumentElement().normalize();
		 
			NodeList nList = document.getElementsByTagName("news");
		 
			for (int temp = 0; temp < nList.getLength(); temp++) {
		 
				Node nNode = nList.item(temp);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					if(eElement.getAttribute("type").equals(news.getType().toString()))
					{
			            Node nodeHeader = eElement.getElementsByTagName("header").item(0).getFirstChild();
			            nodeHeader.setNodeValue(news.getHeader().toString());
			            
			            Node nodeContent = eElement.getElementsByTagName("content").item(0).getFirstChild();
			            nodeContent.setNodeValue(news.getContent().toString());
			            
			            Node nodeFooter = eElement.getElementsByTagName("footer").item(0).getFirstChild();
			            nodeFooter.setNodeValue(news.getFooter().toString());
					}	
				}
			  }
			Transformer tFormer =TransformerFactory.newInstance().newTransformer();
            tFormer.setOutputProperty(OutputKeys.METHOD, "xml");

            Source source = new DOMSource(document);
            Result result = new StreamResult(xmlFile);
			tFormer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteModal(String type) {
		try {
        	File xmlFile = new File(XML_LOCATION);
        	
        	DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);

            document.getDocumentElement().normalize();
		 
			NodeList nList = document.getElementsByTagName("news");
		 
			for (int temp = 0; temp < nList.getLength(); temp++) {
		 
				Node nNode = nList.item(temp);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					if(eElement.getAttribute("type").equals(type))
					{
			            eElement.getParentNode().removeChild(nNode);
					}	
				}
			  }
			Transformer tFormer =TransformerFactory.newInstance().newTransformer();
            tFormer.setOutputProperty(OutputKeys.METHOD, "xml");

            Source source = new DOMSource(document);
            Result result = new StreamResult(xmlFile);
			tFormer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author rvishwakarma
	 * @param types
	 */
	@Override
	public void deleteModals(String types) {
		
		String[] typeStr;
			typeStr  = types.split(",");
			
		int StrLength = typeStr.length-1;	
		int counter;
			
		
			try {
	        	File xmlFile = new File(XML_LOCATION);
	        	
	        	DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
	            Document document = documentBuilder.parse(xmlFile);

	            document.getDocumentElement().normalize();
			 
				NodeList nList = document.getElementsByTagName("news");
			 
				for (int temp = 0; temp < nList.getLength(); temp++) {
			 
					Node nNode = nList.item(temp);
					
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			 
						Element eElement = (Element) nNode;
			 
						//initialize counter
						counter = -1;
						while(++counter <= StrLength)
						{
							if(eElement.getAttribute("type").equals(typeStr[counter]))
							{
								eElement.getParentNode().removeChild(nNode);
							}	
						}
					}
				  }
				Transformer tFormer =TransformerFactory.newInstance().newTransformer();
	            tFormer.setOutputProperty(OutputKeys.METHOD, "xml");

	            Source source = new DOMSource(document);
	            Result result = new StreamResult(xmlFile);
				tFormer.transform(source, result);
			} catch (TransformerException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
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
		
		try {
			File fXmlFile = new File(XML_LOCATION);
			//InputStream is = getClass().getResourceAsStream("/resources/news.xml");//from resource folder
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			Document doc = dBuilder.parse(fXmlFile);
			//Document doc = dBuilder.parse(is);//from resource folder 
			doc.getDocumentElement().normalize();
		 
			NodeList nList = doc.getElementsByTagName("news");
		 
			for (int temp = 0; temp < nList.getLength(); temp++) {
		 
				Node nNode = nList.item(temp);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					if(eElement.getAttribute("type").equals(type))
					{
						news.setType(eElement.getAttribute("type"));
						news.setHeader(eElement.getElementsByTagName("header").item(0).getTextContent());
						news.setContent(eElement.getElementsByTagName("content").item(0).getTextContent());
						news.setFooter(eElement.getElementsByTagName("footer").item(0).getTextContent());
                        news.setImageName(eElement.getElementsByTagName("newsImageName").item(0).getTextContent());
						//news.setNewsImageData(Base64.decodeBase64(eElement.getElementsByTagName("newsImage").item(0).getTextContent()));
						
						String abc = eElement.getElementsByTagName("newsImage").item(0).getTextContent();
						byte[] someArray = abc.getBytes();
						
						news.setNewsImageData(someArray);
						
						news.setNewsImageUploaded(Boolean.valueOf(eElement.getElementsByTagName("newsImageUploaded").item(0).getTextContent()));
					}	
				}
			  }
		    } catch (Exception e) {
		    		e.printStackTrace();
		    }
		return news;
	}

	/**
	 * @author rvishwakarma
	 * @param news
	 */
	@Override
	public void uploadNewsImage(News news) {
		// TODO Auto-generated method stub
		
	}
}
