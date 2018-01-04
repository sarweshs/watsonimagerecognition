package org.sarwesh.example.watsonvisualsearch.util;

import java.io.File;
import java.io.FileNotFoundException;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.Classifier;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.CreateClassifierOptions;

public class CustomClassifier {
	/* static {
		System.setProperty("http.proxyHost", "proxy");
	    System.setProperty("http.proxyPort", "8080");
	    System.setProperty("https.proxyHost", "proxy");
	    System.setProperty("https.proxyPort", "8080");
	    System.setProperty("http.nonProxyHosts","*.xyz.com");
	} */
	public static void main1(String[] args) throws FileNotFoundException {
		VisualRecognition service = new VisualRecognition(
				  VisualRecognition.VERSION_DATE_2016_05_20
				);
				service.setApiKey("api_key");
				
				CreateClassifierOptions createClassifierOptions = new CreateClassifierOptions.Builder()
						  .name("hm_products")
						  .addClass("80924_82285_82679_90389_92484_positive_examples", new File("C:\\svnwork\\Tutorials\\IBM_WATSON\\visual_search\\trial_2\\tops1.zip"))
						  .addClass("83196_90728_90916_91992_92055_92166_positive_examples", new File("C:\\svnwork\\Tutorials\\IBM_WATSON\\visual_search\\trial_2\\tops2.zip"))
						  .addClass("49916_58035_81034_81141_positive_examples", new File("C:\\svnwork\\Tutorials\\IBM_WATSON\\visual_search\\trial_2\\hoodies1.zip"))
						  .addClass("73331_75223_81240_positive_examples", new File("C:\\svnwork\\Tutorials\\IBM_WATSON\\visual_search\\trial_2\\hoodies2.zip"))
						  .addClass("37104_76939_77502_positive_examples", new File("C:\\svnwork\\Tutorials\\IBM_WATSON\\visual_search\\trial_2\\kidsparty1.zip"))
						  .addClass("70409_77891_79914_81584_positive_examples", new File("C:\\svnwork\\Tutorials\\IBM_WATSON\\visual_search\\trial_2\\kidsparty2.zip"))
						  .addClass("10872_40852_42007_48786_71966_positive_examples", new File("C:\\svnwork\\Tutorials\\IBM_WATSON\\visual_search\\trial_2\\rugs1.zip"))
						  .addClass("12930_70494_70636_70731_72681_72683_73670_75024_positive_examples", new File("C:\\svnwork\\Tutorials\\IBM_WATSON\\visual_search\\trial_2\\rugs2.zip"))
						  .build();

						Classifier hm_products = service.createClassifier(createClassifierOptions).execute();
						System.out.println(hm_products);

				/*CreateClassifierOptions createClassifierOptions = new CreateClassifierOptions.Builder()
				  .name("dogs")
				  .addClass("beagle", new File("./beagle.zip"))
				  .addClass("goldenretriever",new File("./golden-retriever.zip"))
				  .addClass("husky", new File("./husky.zip"))
				  .negativeExamples(new File("./cats.zip"))
				  .build();

				Classifier dogs = service.createClassifier(createClassifierOptions).execute();
				System.out.println(dogs);*/
	}
}
