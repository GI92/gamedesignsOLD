package com.gamedesigns.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gamedesigns.entities.User;
import com.gamedesigns.services.UserServices;

@Controller
public class RegisterController {

	@Autowired
	private UserServices userServices;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("register");
		User user = new User();
		model.addObject("userForm", user);
		return model;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String executeLogin(HttpServletRequest request, HttpServletResponse response,
			@Valid @ModelAttribute("userForm") User user, BindingResult result, Map<String, Object> model) {
		// User standard input validation
		if (result.hasErrors()) {
			return "register";
		} else {
			user.setAdministrator(false);
			userServices.add(user);
			return "login";
		}
	}
}
