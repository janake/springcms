package org.prodet.repository.repository;

import org.prodet.repository.dao.Type;
import org.prodet.repository.dao.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TypeRepositoryInterface extends CrudRepository<Type, Long>{

    @Query("select t from Type t where (t.typeName=:type)")
    Type findByName(@Param("type") String type);

    @Query("select t from Type t " +
        "where (t.createdBy= :user and t.visibility='Private') " +
        " or (t.visibility='Authenticated' or t.visibility='Public')" +
        " or t.visibility is NULL")
    Iterable<Type> findAllByUser(@Param("user") User user);

    @Query("select t from Type t where (t.visibility='Public' or t.visibility is NULL)")
    Iterable<Type> findAllByPublic();

}
