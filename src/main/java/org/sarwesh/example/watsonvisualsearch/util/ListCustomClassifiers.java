package org.sarwesh.example.watsonvisualsearch.util;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.Classifiers;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ListClassifiersOptions;

public class ListCustomClassifiers {
	/* static {
		System.setProperty("http.proxyHost", "proxy");
	    System.setProperty("http.proxyPort", "8080");
	    System.setProperty("https.proxyHost", "proxy");
	    System.setProperty("https.proxyPort", "8080");
	    System.setProperty("http.nonProxyHosts","*.xyz.com");
	} */
	public static void main1(String[] args) {
		// TODO Auto-generated method stub
		VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);
		service.setApiKey("api_key");

		ListClassifiersOptions listClassifiersOptions = new ListClassifiersOptions.Builder().verbose(true).build();
		Classifiers classifiers = service.listClassifiers(listClassifiersOptions).execute();
		System.out.println(classifiers);
	}

}
