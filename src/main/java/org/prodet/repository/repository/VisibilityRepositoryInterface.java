package org.prodet.repository.repository;

import org.prodet.repository.dao.Type;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface VisibilityRepositoryInterface extends CrudRepository<Type, Long>{

    @Query("select n from Type n where (n.typeName=:type)")
    Type findByName(@Param("type") String type);

}
