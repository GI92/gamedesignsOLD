package com.gamedesigns.controller;

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
public class LoginController {

	@Autowired
	private UserServices userServices;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("login");
		User user = new User();
		model.addObject("userForm", user);
		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response,
			@Valid @ModelAttribute("userForm") User user, BindingResult result) {
		ModelAndView model = null;
		try {
			boolean isValidUser = userServices.isValidUser(user.getUsername(), user.getPassword());
			if (isValidUser) {
				request.setAttribute("loggedInUser", user.getUsername());
				request.getSession().setAttribute("User", userServices.get(user.getUsername(), user.getPassword()));
				model = new ModelAndView("reset");
			} else {
				model = new ModelAndView("login");
				model.addObject("userForm", user);
				result.reject("error", "Invalid credentials!!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("User");
		return "reset";
	}
}
