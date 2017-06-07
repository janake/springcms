package org.prodet.web;

import org.prodet.repository.domain.ContentEntity;
import org.prodet.repository.repository.ContentEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EntityController {
	
	@Autowired
	ContentEntityRepository ceRepo;

	@RequestMapping(value="/entity/new", method=RequestMethod.GET)
	public String setNewContent(Model model) {
		ContentEntity ce = ceRepo.findByname("article");
		
		model.addAttribute("fragment", "newEntity.ftl");
		return "index";
	}
	
}
