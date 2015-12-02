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
import com.gamedesigns.entities.GameType;
import com.gamedesigns.entities.User;
import com.gamedesigns.services.GameTypeServices;

@Controller
public class GameTypeController {

	@Autowired
	private GameTypeServices gameTypeServices;

	@RequestMapping(value = "/gametypelist", method = RequestMethod.GET)
	public ModelAndView displayGameTypeList(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("User");
		if (user == null || !user.isAdministrator()) {
			return new ModelAndView("ACCESS DENIED");
		} else {
			ModelAndView model = new ModelAndView("admin/gametypelist");
			GameType gameType = new GameType();
			model.addObject("gametypelist", gameTypeServices.getList());
			model.addObject("gameTypeForm", gameType);
			return model;
		}
	}

	// Method to add user
	@RequestMapping(value = "/gametypelist/{name}/{id}", method = RequestMethod.POST)
	public ModelAndView executeAddCategory(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("name") String typeName, @PathVariable("id") Long typeID,
			@ModelAttribute("categoryForm") Category category,
			@ModelAttribute("gameTypeForm") GameType gameType, BindingResult result) {
		User user = (User) request.getSession().getAttribute("User");
		if (user == null || !user.isAdministrator()) {
			return new ModelAndView("ACCESS DENIED");
		} else {
			gameType = new GameType();
			if (!typeID.equals(new Long(0))) {
				gameType.setID(typeID);
			}
			gameType.setName(typeName);
			if (!gameTypeServices.exist(gameType)) {
				// Check if it is update or add request
				if (gameType.getID() != null) {
					gameTypeServices.update(gameType);
				} else {
					gameTypeServices.add(gameType);
				}
			} else {
				result.reject("error", "Game type already exist!!!");
			}
			ModelAndView model = new ModelAndView("admin/gametypelist");
			model.addObject("gametypelist", gameTypeServices.getList());
			model.addObject("gameTypeForm", new GameType());
			return model;
		}
	}

	// JSP
	// @RequestMapping(value = "/category/remove/{id}", method =
	// RequestMethod.DELETE)
	@RequestMapping(value = "/gametype/remove/{id}", method = RequestMethod.POST)
	public String executeRemoveCategory(@PathVariable("id") Long gameTypeID) {
		GameType gameType = new GameType();
		gameType.setID(gameTypeID);
		gameTypeServices.delete(gameType);
		return "redirect:../../gametypelist";
	}

}
