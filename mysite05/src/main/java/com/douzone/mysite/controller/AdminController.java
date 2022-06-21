package com.douzone.mysite.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.FileUploadService;
import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;

@Auth(role="ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping("")
	public String main(Model model) {
		SiteVo site = siteService.getSite();
		model.addAttribute("site", site);
		return "admin/main";
	}

	@RequestMapping("/main/update")
	public String main(SiteVo site, @RequestParam("file") MultipartFile file) {
		String profileurl = fileUploadService.restoreImage(file);
		
		if(profileurl != null) {
			site.setProfileUrl(profileurl);
		}
		
		siteService.update(site);
		servletContext.setAttribute("site", site);
		return "redirect:/admin";
	}
	
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}

	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}

	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}	
}
