package com.gamedesigns.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gamedesigns.services.PreviewServices;

@Controller
public class PreviewController {

	@Autowired
	private PreviewServices previewServices;
	
	@RequestMapping(value = "/design/update/deletePreview/{id}", method = RequestMethod.GET)
	public ModelAndView updateDesign(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") Long previewID) {
		previewServices.delete(previewServices.get(previewID));
		return new ModelAndView();

	}
}
