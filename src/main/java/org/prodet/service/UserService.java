package org.prodet.service;

import org.prodet.configuration.security.CustomUserDetails;
import org.prodet.repository.dao.User;
import org.prodet.repository.repository.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserService {
	
	@Autowired
	UserRepositoryInterface userRepo;

	public User findByuserNameOrEmail(String userName) {
		User user = userRepo.findByuserNameOrEmail(userName);
		return user;
	}

	public User getUserFromPrincipal(Principal principal) {
		if (principal != null ) {
			String userName = ((CustomUserDetails)(((UsernamePasswordAuthenticationToken) principal)
					.getPrincipal()))
					.getUsername();
			User user = userRepo.findByuserNameOrEmail(userName);
			return user;
		}
		return null;
	}

}
