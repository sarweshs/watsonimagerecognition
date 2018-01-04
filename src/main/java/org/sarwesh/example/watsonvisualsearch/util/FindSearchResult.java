package org.sarwesh.example.watsonvisualsearch.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;

public class FindSearchResult {
	/* static {
		System.setProperty("http.proxyHost", "proxy");
	    System.setProperty("http.proxyPort", "8080");
	    System.setProperty("https.proxyHost", "proxy");
	    System.setProperty("https.proxyPort", "8080");
	    System.setProperty("http.nonProxyHosts","*.xyz.com");
	} */
	public static void main1(String[] args) throws FileNotFoundException {
		VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);
		service.setApiKey("api_key");

		String path = "C:\\svnwork\\Tutorials\\IBM_WATSON\\visual_search\\find_product\\earcover.jpg";
		InputStream imagesStream = new FileInputStream(path);
		ClassifyOptions classifyOptions = new ClassifyOptions.Builder().imagesFile(new File(path))//.imagesFile(imagesStream)
				.imagesFilename("earcover.jpg").imagesFileContentType("image/jpeg")
				.parameters("{\"classifier_ids\": [\"hm_products_816068037\"]," + "\"owners\": [\"IBM\", \"me\"],\"threshold\":0.4}")
				.build();
		
		ClassifiedImages result = service.classify(classifyOptions).execute();
		System.out.println(result);
	}
	
	public static ClassifiedImages getWatsonVisualSearchResult(MultipartFile file) throws IOException
	{
		VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);
		service.setApiKey("api_key");
		InputStream imagesStream =  new BufferedInputStream(file.getInputStream());
		ClassifyOptions classifyOptions = new ClassifyOptions.Builder().imagesFile(imagesStream)//imagesFile(new File(path))//.
				.imagesFilename("earcover.jpg").imagesFileContentType("image/jpeg")
				.parameters("{\"classifier_ids\": [\"hm_products_816068037\"]," + "\"owners\": [\"IBM\", \"me\"],\"threshold\":0.4}")
				.build();
		
		ClassifiedImages result = service.classify(classifyOptions).execute();
		return result;
	}
}
