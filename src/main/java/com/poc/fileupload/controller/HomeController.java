package com.poc.fileupload.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.poc.fileupload.dao.FileUploadDAO;
import com.poc.fileupload.model.UploadFile;

/**
 * Handles requests for the file upload page.
 */
@Controller
public class HomeController {
	@Autowired
	private FileUploadDAO fileUploadDao;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showUploadForm(HttpServletRequest request) {
		return "Upload";
	}
	
    @RequestMapping(value = "/doUpload", method = RequestMethod.POST)
    public String handleFileUpload(HttpServletRequest request,
            @RequestParam CommonsMultipartFile[] fileUpload) throws Exception {
         
        if (fileUpload != null && fileUpload.length > 0) {
            for (CommonsMultipartFile aFile : fileUpload){
                 
                System.out.println("Saving file: " + aFile.getOriginalFilename());
                
                UploadFile uploadFile = new UploadFile();
                uploadFile.setFileName(aFile.getOriginalFilename());
                uploadFile.setData(aFile.getBytes());
                fileUploadDao.save(uploadFile);                
            }
        }
 
        return "Success";
    }
    
    @RequestMapping(value = "/files/{file_name}", method = RequestMethod.GET)
    @ResponseBody 
    public void getFile(@PathVariable("file_name") String fileName,HttpServletRequest request, HttpServletResponse response) throws IOException {
    	UploadFile fileUpload = fileUploadDao.getFileFor(fileName);
		
    	response.setHeader("Content-Disposition", "inline; filename=\"report.zip\"");
        response.setDateHeader("Expires", -1);
        response.setContentType("application/zip");
        response.setContentLength(fileUpload.getData().length);
        response.getOutputStream().write(fileUpload.getData());
        
    }
}
