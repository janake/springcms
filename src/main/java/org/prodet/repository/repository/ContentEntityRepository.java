package org.prodet.repository.repository;

import org.prodet.repository.domain.ContentEntity;
import org.springframework.data.repository.CrudRepository;

public interface ContentEntityRepository extends CrudRepository<ContentEntity, Long> {

	ContentEntity findByname(String name);

}
