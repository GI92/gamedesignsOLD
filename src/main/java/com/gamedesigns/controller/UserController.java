package com.gamedesigns.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class UserController {
	
	@Autowired
	private UserServices userServices;
	
	@RequestMapping(value = "/accountdetails", method = RequestMethod.GET)
	public ModelAndView displayUserDetails(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("User");
		if(user == null){
			return new ModelAndView("ACCESS_DENIED");
		} else {
			ModelAndView model = new ModelAndView("user/accountdetails");
			model.addObject("userForm", user);
			return model;
		}
	}
	
	@RequestMapping(value = "/accountdetails", method = RequestMethod.POST)
	public ModelAndView modifyUserDetails(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("userForm") User user, BindingResult result) {
		User checkUser = (User) request.getSession().getAttribute("User");
		if(checkUser == null){
			return new ModelAndView("ACCESS_DENIED");
		} else {
			List<User> users = userServices.get(user.getUsername(), user.getEmail(), checkUser.getID());
			if (users != null && users.size() != 0){
				result.reject("error", "Update data rejected");
			}
			if(!result.hasErrors()){
				checkUser.setUsername(user.getUsername());
				checkUser.setPassword(user.getPassword());
				checkUser.setEmail(user.getEmail());
				userServices.update(checkUser);
			}
			ModelAndView model = new ModelAndView("user/accountdetails");
			model.addObject("userForm", user);
			return model;
		}		
	}
}
