package org.sarwesh.example.watsonvisualsearch.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.sarwesh.example.watsonvisualsearch.util.FindSearchResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassResult;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImage;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifierResult;

@Controller
public class UploadController {

    //Save the uploaded file to this folder
	private static String UPLOADED_FOLDER = "C://temp//";
    private static String HM_COM_SITE  = "http://www.hm.com/se/product/";

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @PostMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            //Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
           // Files.write(path, bytes);
            ClassifiedImages result = FindSearchResult.getWatsonVisualSearchResult(file);
            List<String> targetUrls = getListOfUrls(result);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
            
            redirectAttributes.addFlashAttribute("sites",
            		targetUrls);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }
    
    private List<String> getListOfUrls(ClassifiedImages result) {
		// TODO Auto-generated method stub
    	List<String> list = new ArrayList<>();
    	for(ClassifiedImage image:result.getImages())
    	{
    		for(ClassifierResult res:image.getClassifiers())
    		{
    			for(ClassResult classres:res.getClasses())
    			{
    				if(classres == null || classres.getClassName() == null)
    				{
    					continue;
    				}
    				for(String str:classres.getClassName().split("_"))
    				{
    					list.add(HM_COM_SITE + str); 
    				}
    			}
    		}
    	}
		return list;
	}

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

}