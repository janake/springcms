package org.prodet.service;

import java.util.Map;

import org.prodet.repository.domain.ContentEntity;
import org.prodet.repository.domain.ContentEntityItem;
import org.prodet.repository.repository.ContentEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentEntityService {
	
	@Autowired
	ContentEntityRepository contentEntityRepo;

	public ContentEntity createNewContentEntity(
			String name, Map<String, ContentEntityItem> contentEntityItems) {
		
		ContentEntity contentEntity = new ContentEntity(name, contentEntityItems);
		
		return contentEntity;
		
	}
	
}
