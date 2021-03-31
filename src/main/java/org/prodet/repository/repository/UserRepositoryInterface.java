package org.prodet.repository.repository;

import org.prodet.repository.dao.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepositoryInterface extends CrudRepository<User, Long> {

	@Query("select u from User u where (u.email=:username OR u.userName=:username)")
	User findByuserNameOrEmail(@Param("username")String username);

}
