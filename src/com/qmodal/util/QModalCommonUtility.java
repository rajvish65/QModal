/**
 * 
 */
package com.qmodal.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.codec.binary.Base64;

/**
 * @author RVishwakarma
 *
 */
public class QModalCommonUtility {
	
	public static final String ROLE_REGISTERED = "ROLE_REGISTERED";
	public static final String ROLE_STANDARDUSER = "STANDARD USER";
	public static final String ROLE_ADMINISTRATOR = "ADMINISTRATOR";

	/**
	 * Returns the FileExtension of the File (i.e jpg if the filename is abc.jpg)
	 * @author rvishwakarma
	 * @param imageName
	 * @return String(fileExtension)
	 */
	public static String getFileExtension(String imageName) {
		String fileExtension = "";
		if(imageName.contains("."))
		{
			fileExtension = imageName.substring(imageName.lastIndexOf(".")+1,imageName.length());
		}
		return fileExtension;
	}
		
		 /** Compresses the Original Image and Returns it
		 * @author rvishwakarma
		 * @param photoData
		 * @return compressedImageInBytes(byte[])
		 * @throws IOException
		 */
		 public static byte[] returnCompressedImage(byte[] photoData) throws IOException
		 {
			   		InputStream inputStream = new ByteArrayInputStream(photoData);
			   		
			   		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			        
			        ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(byteArrayOutputStream);

			        float quality = 0.2f;
			 
			        // create a BufferedImage as the result of decoding the supplied InputStream
			        BufferedImage image = ImageIO.read(inputStream);
			 
			        // get all image writers for JPG format
			        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
			        
			        if (!writers.hasNext())
			            throw new IllegalStateException("No writers found");
			 
			        ImageWriter writer = (ImageWriter) writers.next();
			        writer.setOutput(imageOutputStream);
			        
			        ImageWriteParam param = writer.getDefaultWriteParam();
			 
			        // compress to a given quality
			        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			        param.setCompressionQuality(quality);
			        //param.setDestinationType(ImageTypeSpecifier.)
			 
			        // appends a complete image stream containing a single image and associated stream and image metadata and thumbnails to the output
			        writer.write(null, new IIOImage(image, null, null), param);
			 
			        byte[] compressedImageInBytes = byteArrayOutputStream.toByteArray();
			        return compressedImageInBytes;
			}

}
