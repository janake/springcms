package org.prodet.service;

import org.prodet.repository.dao.User;
import org.prodet.repository.repository.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	UserRepositoryInterface userRepo;

	public User findByuserNameOrEmail(String userName) {
		User user = userRepo.findByuserNameOrEmail(userName);
		return user;
	}

}
