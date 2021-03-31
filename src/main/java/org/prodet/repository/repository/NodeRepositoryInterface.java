package org.prodet.repository.repository;

import org.prodet.repository.dao.Node;
import org.prodet.repository.dao.Type;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface NodeRepositoryInterface extends CrudRepository<Node, Long> {

    @Query("select n from Node n where (n.type=:type)")
    Iterable<Node> findAllByType(@Param("type") Type type);

}
