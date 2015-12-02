package com.gamedesigns.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gamedesigns.entities.Category;
import com.gamedesigns.entities.User;
import com.gamedesigns.services.CategoryServices;
import com.gamedesigns.services.GameTypeServices;

@Controller
public class CategoryController {

	@Autowired
	private GameTypeServices gameTypeServices;

	@Autowired
	private CategoryServices categoryServices;

	// Category list content
	@RequestMapping(value = "/categorylist", method = RequestMethod.GET)
	public ModelAndView displayCategoryList(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("User");
		if (user == null || !user.isAdministrator()) {
			return new ModelAndView("error/ACCESS_DENIED");
		} else {
			ModelAndView model = new ModelAndView("admin/categorylist");
			Category category = new Category();
			model.addObject("categorylist", categoryServices.getList());
			model.addObject("categoryForm", category);
			return model;
		}
	}

	// Method to add user
	@RequestMapping(value = "/categorylist/{name}/{id}", method = RequestMethod.POST)
	public ModelAndView executeAddCategory(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("name") String categoryName, @PathVariable("id") Long categoryID,
			@ModelAttribute("categoryForm") Category category,BindingResult result) {
		User user = (User) request.getSession().getAttribute("User");
		if (user == null || !user.isAdministrator()) {
			return new ModelAndView("error/ACCESS_DENIED");
		} else {
			ModelAndView model = new ModelAndView("admin/categorylist");
			category = new Category();
			if (!categoryID.equals(new Long(0))) {
				category.setID(categoryID);
			}
			category.setName(categoryName);
			if (!categoryServices.exist(category)) {
				// Check if it is update or add request
				if (category.getID() != null) {
					categoryServices.update(category);
				} else {
					categoryServices.add(category);
				}
			} else {
				result.reject("error", "Category already exist!!!");
			}
			model.addObject("categorylist", categoryServices.getList());
			return model;

		}

	}

	// JSP
	// @RequestMapping(value = "/category/remove/{id}", method =
	// RequestMethod.DELETE)
	@RequestMapping(value = "/category/remove/{id}", method = RequestMethod.POST)
	public String executeRemoveCategory(@PathVariable("id") Long categoryID) {
		Category category = new Category();
		category.setID(categoryID);
		categoryServices.delete(category);
		return "redirect:../../categorylist";
	}

	@RequestMapping(value = "/categorydetails", method = RequestMethod.GET)
	public ModelAndView menuCategoryDetailsList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("categorydetails");
		model.addObject("gametypelist", gameTypeServices.getList());
		return model;
	}
}
