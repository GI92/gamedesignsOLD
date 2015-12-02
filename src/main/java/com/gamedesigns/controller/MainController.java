package com.gamedesigns.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gamedesigns.entities.Design;
import com.gamedesigns.entities.Preview;
import com.gamedesigns.entities.User;
import com.gamedesigns.services.CategoryServices;
import com.gamedesigns.services.DesignServices;
import com.gamedesigns.services.PreviewServices;

@Controller
public class MainController {

	@Autowired
	private CategoryServices categoryServices;

	@Autowired
	private DesignServices designServices;
	
	@Autowired
	private PreviewServices previewServices;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String mainMenuGet(HttpServletRequest request, HttpServletResponse response) {
		return "index";
	}

	// Menu content
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public ModelAndView menuCategoryList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("categorylist");
		model.addObject("categorylist", categoryServices.getList());
		return model;
	}

	// Return design image
	@RequestMapping(value = "/image/{Id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> designImage(@PathVariable("Id") long designID) throws IOException {
		Design design = designServices.get(designID);
		byte[] image = design.getDefaultImage();
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(image, headers, HttpStatus.CREATED);
	}

	// Return preview image
		@RequestMapping(value = "/image/preview/{Id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
		public ResponseEntity<byte[]> previewImage(@PathVariable("Id") long previewID) throws IOException {
			Preview preview = previewServices.get(previewID);
			if(preview == null){
				preview = new Preview();
			}
			byte[] image = preview.getImage();
			final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);
			return new ResponseEntity<byte[]>(image, headers, HttpStatus.CREATED);
		}
	
	@RequestMapping(value = "/logged", method = RequestMethod.GET)
	public ModelAndView checkUserStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User user = (User) request.getSession().getAttribute("User");
		ModelAndView model = null;
		if (user == null){
			model = new ModelAndView("loginregister");
		} else if(user.isAdministrator()){
			model = new ModelAndView("admin/userbar");
			model.addObject("username", user.getUsername());
		} else {
			model = new ModelAndView("user/userbar");
			model.addObject("username", user.getUsername());
		}
		return model;
	}
	
}
