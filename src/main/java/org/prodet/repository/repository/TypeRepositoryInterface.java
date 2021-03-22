package org.prodet.repository.repository;

import org.prodet.repository.domain.Node;
import org.prodet.repository.domain.Type;
import org.prodet.service.dao.TypeView;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TypeRepositoryInterface extends CrudRepository<Type, Long>{

    @Query("select n from Type n where (n.typeName=:type)")
    Type findByName(@Param("type") String type);

}
