package org.prodet.service;

import org.prodet.repository.domain.User;
import org.prodet.repository.repository.UserRepositoryInterface;
import org.prodet.service.dao.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	UserRepositoryInterface userRepo;

	public UserView findByuserNameOrEmail(String userName) {
		User user = userRepo.findByuserNameOrEmail(userName);
		UserView userView = new UserView(user);
		return userView;
	}

}
