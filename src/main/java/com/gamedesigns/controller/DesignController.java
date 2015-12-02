package com.gamedesigns.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gamedesigns.entities.Category;
import com.gamedesigns.entities.Design;
import com.gamedesigns.entities.GameType;
import com.gamedesigns.entities.Preview;
import com.gamedesigns.entities.User;
import com.gamedesigns.services.CategoryServices;
import com.gamedesigns.services.DesignServices;
import com.gamedesigns.services.GameTypeServices;
import com.gamedesigns.services.PreviewServices;
import com.gamedesigns.services.UserServices;

@Controller
public class DesignController {

	@Autowired
	private UserServices userServices;

	@Autowired
	private CategoryServices categoryServices;

	@Autowired
	private GameTypeServices gameTypeServices;

	@Autowired
	private DesignServices designServices;
	
	@Autowired
	private PreviewServices previewServices;

	@RequestMapping(value = "/createdesign", method = RequestMethod.GET)
	public ModelAndView menuCreateDesignGet(HttpServletRequest request, HttpServletResponse response) {

		User user = (User) request.getSession().getAttribute("User");
		if (user == null) {
			return new ModelAndView("ACCESS DENIED");
		} else {
			ModelAndView model = new ModelAndView("user/createdesign");
			Design design = new Design();
			model.addObject("designForm", design);
			model.addObject("categorylist", categoryServices.getList());
			model.addObject("gametypelist", gameTypeServices.getList());
			return model;
		}
	}

	@RequestMapping(value = "/createdesign", method = RequestMethod.POST)
	public ModelAndView menuCreateDesignPost(HttpServletRequest request, HttpServletResponse response,
			@Valid @ModelAttribute("designForm") Design design, BindingResult result,
			@RequestParam("fileUpload") MultipartFile file, @RequestParam("archiveUpload") MultipartFile archive)
					throws IOException {
		User user = (User) request.getSession().getAttribute("User");
		if (user == null) {
			return new ModelAndView("ACCESS_DENIED");
		} else {
			String categoryIDStr = request.getParameter("categoryID");
			String[] gametypeIDS = request.getParameterValues("gametype");
			if (gametypeIDS == null) {
				result.reject("gametypeError", "Select one or more game types");
			}

			if (file.isEmpty() || archive.isEmpty()) {
				result.reject("gametypeError", "Load source code and/or default images");
			} else {
				design.setFileName(file.getOriginalFilename());
				design.setDefaultImage(file.getBytes());
				design.setArchiveName(archive.getOriginalFilename());
				design.setSourcecode(archive.getBytes());
			}

			if (result.hasErrors()) {
				ModelAndView model = new ModelAndView("user/createdesign");
				model.addObject("categorylist", categoryServices.getList());
				model.addObject("designForm", design);
				model.addObject("categorylist", categoryServices.getList());
				model.addObject("gametypelist", gameTypeServices.getList());
				return model;
			} else {
				design.setUser(userServices.get(user.getID()));
				Long categoryID = new Long(categoryIDStr);
				Category category = categoryServices.get(categoryID);
				Set<GameType> gametypes = new HashSet<>();
				for (String gametypeIDStr : gametypeIDS) {
					GameType gameType = gameTypeServices.get(new Long(gametypeIDStr));
					gametypes.add(gameType);
				}
				design.setCategory(category);
				design.setGameTypes(gametypes);
				designServices.add(design);
				return new ModelAndView("/reset");
			}
		}
	}

	@RequestMapping(value = "/designlist/{id}", method = RequestMethod.GET)
	public ModelAndView changeDetailView(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") Long categoryID) {
		ModelAndView model = new ModelAndView("designlist");
		if (categoryID == null) {
			return model;
		} else {
			Category category = categoryServices.getWithDesings(new Long(categoryID));
			request.setAttribute("designlist", category.getDesigns());
			return model;
		}
	}

	@RequestMapping(value = "/designlist/{id1}/{id2}", method = RequestMethod.POST)
	public ModelAndView changeFilterDetailView(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id1") Long categoryID, @PathVariable("id2") String gameTypeID) {
		ModelAndView model = new ModelAndView("designlist");
		if (categoryID == null) {
			return model;
		} else {
			Category category = categoryServices.getWithDesings(new Long(categoryID));
			if (gameTypeID == null) {
				request.setAttribute("designlist", category.getDesigns());
				return model;
			} else {
				String[] ids = gameTypeID.split(",");
				List<Long> idList = new ArrayList<>();
				for (String id : ids) {
					idList.add(new Long(id));
				}
				request.setAttribute("designlist", designServices.getFiltredList(category, idList));
				return model;
			}
		}
	}

	@RequestMapping(value = "/userdesignlist", method = RequestMethod.GET)
	public ModelAndView getUserDesignList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("designlist");
		User user = (User) request.getSession().getAttribute("User");
		if (user == null) {
			return model;
		} else {
			user = userServices.getWithDesings(new Long(user.getID()));
			request.setAttribute("designlist", user.getDesigns());
			return model;
		}
	}

	@RequestMapping(value = "/viewdesign/{id}", method = RequestMethod.GET)
	public ModelAndView viewDesign(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") Long designID) {
		ModelAndView model = new ModelAndView("viewdesign");
		if (designID == null) {
			return model;
		}
		User user = (User) request.getSession().getAttribute("User");
		if (user == null) {
			Design design = designServices.getWithPreview(new Long(designID));
			if (design == null) {
				return model;
			}
			model.addObject("designpreview", design.getImages());
			model.addObject("design", design);
			return model;
		} else {
			Design design = designServices.getComplete(new Long(designID));
			if (design == null) {
				return model;
			}
			if (user.isAdministrator() || user.getID().equals(design.getUser().getID())) {
				model = new ModelAndView("user/viewdesign");
				model.addObject("designpreview", design.getImages());
				model.addObject("design", design);
				return model;
			} else {
				model.addObject("designpreview", design.getImages());
				model.addObject("design", design);
				return model;
			}
		}
	}

	@RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
	public void getFile(@PathVariable("id") Long designID, HttpServletResponse response) {
		Design desing = designServices.get(designID);
		try {
			OutputStream fos = response.getOutputStream();
			response.setHeader("Content-Disposition", "attachment;filename=".concat(desing.getName() + ".zip"));
			fos.write(desing.getSourcecode());
			response.flushBuffer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/design/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteDesign(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") Long designID) {
		if (designID == null) {
			return new ModelAndView("error/ACCESS_DENIED");
		}
		User user = (User) request.getSession().getAttribute("User");
		if (user == null) {
			return new ModelAndView("error/ACCESS_DENIED");
		} else {
			Design design = designServices.getComplete(new Long(designID));
			if (user.isAdministrator() || user.getID().equals(design.getUser().getID())) {
				designServices.delete(design);
				return new ModelAndView("reset");
			} else {
				return new ModelAndView("error/ACCESS_DENIED");
			}
		}
	}

	@RequestMapping(value = "/design/update/{id}", method = RequestMethod.GET)
	public ModelAndView updateDesign(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") Long designID) {
		if (designID == null) {
			return new ModelAndView("error/ACCESS_DENIED");
		}
		User user = (User) request.getSession().getAttribute("User");
		if (user == null) {
			return new ModelAndView("ACCESS DENIED");
		} else {
			Design design = designServices.getComplete(new Long(designID));
			if (user.isAdministrator() || user.getID().equals(design.getUser().getID())) {
				ModelAndView model = new ModelAndView("user/updatedesign");
				model.addObject("designForm", design);
				model.addObject("imageList", design.getImages());
				return model;
			} else {
				return new ModelAndView("error/ACCESS_DENIED");
			}
		}
	}

	@RequestMapping(value = "/design/update/{id}", method = RequestMethod.POST)
	public ModelAndView updateDesignPost(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") Long designID, @ModelAttribute("designForm") Design design, BindingResult result,
			@RequestParam("fileUpload") MultipartFile file, @RequestParam("archiveUpload") MultipartFile archive,
			@RequestParam("previewUpload") MultipartFile previewFile) {
		if (designID == null) {
			return new ModelAndView("error/ACCESS_DENIED");
		}
		User user = (User) request.getSession().getAttribute("User");
		if (user == null) {
			return new ModelAndView("ACCESS DENIED");
		} else {
			design.setID(designID);
			Design checkDesign = designServices.getComplete(new Long(designID));
			if (user.isAdministrator() || user.getID().equals(design.getUser().getID())) {
				ModelAndView model = new ModelAndView("user/updatedesign");
				if(!previewFile.isEmpty()){
					if(checkDesign.getImages() == null){
						checkDesign.setImages(new HashSet<Preview>());
					}
					Set<Preview> previews = checkDesign.getImages();
					Preview preview = new Preview();
					preview.setName(previewFile.getOriginalFilename());
					try {
						preview.setImage(previewFile.getBytes());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					preview.setDesign(checkDesign);
					previewServices.add(preview);
					previews.add(preview);
					// reset desing with new preview image
					checkDesign = designServices.getComplete(new Long(designID));
				}
				if (!design.getName().equals(checkDesign.getName())) {
					if (designServices.exist(design)) {
						model.addObject("categorylist", categoryServices.getList());
						model.addObject("gametypelist", gameTypeServices.getList());
						result.reject("error", "Design name already exist!!!");
						return model;
					}
				}
				if (!file.isEmpty()) {
					checkDesign.setFileName(file.getOriginalFilename());
					try {
						checkDesign.setDefaultImage(file.getBytes());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (!archive.isEmpty()) {
					checkDesign.setArchiveName(archive.getOriginalFilename());
					try {
						checkDesign.setSourcecode(archive.getBytes());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				designServices.update(checkDesign);
				model.addObject("designForm", checkDesign);
				model.addObject("imageList", checkDesign.getImages());
				return model;
			} else {
				return new ModelAndView("error/ACCESS_DENIED");
			}
		}
	}
}
