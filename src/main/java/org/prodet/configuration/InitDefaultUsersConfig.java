package org.prodet.configuration;

import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import org.prodet.repository.domain.Role;
import org.prodet.repository.domain.User;
import org.prodet.repository.repository.RoleRepositoryInterface;
import org.prodet.repository.repository.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitDefaultUsersConfig {
	
	@Transactional
	@Autowired
	public void init(UserRepositoryInterface userRepository, RoleRepositoryInterface roleRepository) {
		
		ArrayList<Role> janakeRoles = new ArrayList<Role>();
		ArrayList<Role> adminRoles = new ArrayList<Role>();

		roleRepository.save(new Role(1l, "admin"));
		roleRepository.save(new Role(2l, "user"));
		
		Optional<Role> adminRole = roleRepository.findById(1l);
		Optional<Role> userRole = roleRepository.findById(2l);

		if (adminRole.isPresent()) {
			adminRoles.add(adminRole.get());
		}
		if (userRole.isPresent()) {
			adminRoles.add(userRole.get());
			janakeRoles.add(userRole.get());
		}

		User admin = new User("admin", "admin@example.com", 
				"pwd", "", "", true, adminRoles);	
		userRepository.save(admin);

		if (userRole.isPresent()) {
			janakeRoles.add(userRole.get());
		}

		User janake = new User("janake", "janak.endre@gmail.com", 
				"pwd", "", "", true, janakeRoles);
		
		userRepository.save(janake);
		
	}
	
}
