package org.prodet.web;

import org.prodet.service.AdminServiceInterface;
import org.prodet.service.NodeServiceInterface;
import org.prodet.service.TypeService;
import org.prodet.service.dto.TypeDTO;
import org.prodet.service.dto.TypeListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value="/admin", method=RequestMethod.GET)
public class AdminController {

	@Autowired
	private AdminServiceInterface adminService;

	@Autowired
	private NodeServiceInterface nodeService;

	@Autowired
	private TypeService typeService;

	@RequestMapping(value="", method=RequestMethod.GET)
	public String getSettingsPage(Model model, Principal principal) {
		nodeService.addTypesToModel(model, principal);
		return "admin/settings";
	}

	@RequestMapping(value="/settings/navbar", method=RequestMethod.GET)
	public String getNavigationConfig(Model model, Principal principal) {
		nodeService.addTypesToModel(model, principal);
		nodeService.addTypesToModel(new TypeListDTO(), model, principal);
		return "admin/settings";
	}

	@RequestMapping(value="/add/navbar", method=RequestMethod.GET)
	public String addNavigationItem(Model model, Principal principal) {
		nodeService.addTypesToModel(model, principal);
		model.addAttribute("newtype", new TypeDTO());
		return "admin/settings";
	}

	@RequestMapping(value="/settings/navbar/save", method=RequestMethod.POST)
	public String setNavigationConfig(@ModelAttribute TypeListDTO typeListDTO, Model model, Principal principal) {
		List<TypeDTO> validTypeListDTOs = adminService.getValidTypeListDTOs(typeListDTO, principal);
		typeService.save(validTypeListDTOs);
		nodeService.addTypesToModel(model, principal);
		nodeService.addTypesToModel(new TypeListDTO(), model, principal);
		return "admin/settings";
	}

	@RequestMapping(value="/settings/navbar/add", method=RequestMethod.POST)
	public String addItemToNavBar(@ModelAttribute TypeDTO typeDTO, Model model, Principal principal) {
		typeService.save(typeDTO, principal);
		nodeService.addTypesToModel(model, principal);
		return "admin/settings";
	}

}
