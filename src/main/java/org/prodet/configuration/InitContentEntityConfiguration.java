package org.prodet.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.prodet.repository.domain.ContentEntity;
import org.prodet.repository.domain.ContentEntityItem;
import org.prodet.repository.repository.ContentEntityItemRepository;
import org.prodet.repository.repository.ContentEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitContentEntityConfiguration {

	@Autowired
	@Transactional
	public void createDefaultContentEntityItems(ContentEntityItemRepository ceiRepo) {

		String textCss = "form-control";

		ContentEntityItem text = new ContentEntityItem(1l, "formInput", textCss);
		ContentEntityItem textArea = new ContentEntityItem(2l, "formTextarea");

		ceiRepo.save(text);
		ceiRepo.save(textArea);
	}

	@Autowired
	@Transactional
	public void createDefaultContentEntities(ContentEntityRepository ceRepo, 
			ContentEntityItemRepository ceiRepo) {
		
		ContentEntityItem text = ceiRepo.findOne(1l);
		ContentEntityItem textArea = ceiRepo.findOne(2l);
		
		Map<String, ContentEntityItem> contentEntityItem = new HashMap<String, ContentEntityItem>();
		contentEntityItem.put("title", text);
		contentEntityItem.put("body", textArea);
		
		ContentEntity ce = new ContentEntity("article", contentEntityItem);
		ceRepo.save(ce);
		
	}

}
