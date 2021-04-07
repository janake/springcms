package org.prodet.repository.repository;

import org.prodet.repository.dao.Node;
import org.prodet.repository.dao.Type;
import org.prodet.repository.dao.User;
import org.prodet.repository.dao.Visibility;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NodeRepositoryInterface extends CrudRepository<Node, Long> {

    @Query("select n from Node n where (n.type=:type and n.visibility='Public') or n.visibility is NULL")
    Iterable<Node> findAllByType(@Param("type") Type type);

    @Query("select n from Node n " +
            "where (n.type=:type and n.createdBy= :user and (n.visibility='Private')) " +
            " or (n.type=:type and (n.visibility='Authenticated' or n.visibility='Public'))" +
            " or n.visibility is NULL")
    Iterable<Node> findAllByType(@Param("type") Type type, @Param("user") User user);

    @Query("select n from Node n where (n.createdBy=:user and n.id=:id)")
    Optional<Node> findNodeById(@Param("id") long id, @Param("user") User user);

}
