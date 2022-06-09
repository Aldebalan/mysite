package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.mysite.service.FileUploadService;
import com.douzone.mysite.service.GalleryService;

@Controller
@RequestMapping("/gallery")
public class GalleryController {

	@Autowired
	private FileUploadService fileUploadService;
	
	private GalleryService galleryService;
	
	@RequestMapping("")
	public String index() {
		return "gallery/index";
	}
	
}
