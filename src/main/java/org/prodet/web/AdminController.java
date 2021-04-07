package org.prodet.web;

import org.prodet.repository.dao.Node;
import org.prodet.service.NodeServiceInterface;
import org.prodet.service.dto.TypeDTO;
import org.prodet.service.dto.TypeListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/admin", method=RequestMethod.GET)
public class AdminController {

	@Autowired
	private NodeServiceInterface nodeService;

	@RequestMapping(value="", method=RequestMethod.GET)
	public String getSettingsPage() {
		return "admin/settings";
	}

	@RequestMapping(value="/settings/navbar", method=RequestMethod.GET)
	public String getNavigationConfig(@ModelAttribute TypeListDTO types, Model model) {
		types = new TypeListDTO();
		nodeService.addTypesToModel(model);
		nodeService.addTypesToModel(types, model);
		return "admin/settings";
	}

	@RequestMapping(value="/settings/navbar/save", method=RequestMethod.POST)
	public String setNavigationConfig(@ModelAttribute TypeListDTO types, Model model) {
		nodeService.addTypesToModel(model);
		types = new TypeListDTO();
		nodeService.addTypesToModel(types, model);
		return "admin/settings";
	}
}
