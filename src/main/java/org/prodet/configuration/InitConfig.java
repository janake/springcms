package org.prodet.configuration;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.prodet.repository.domain.Role;
import org.prodet.repository.domain.User;
import org.prodet.repository.repository.RoleRepositoryInterface;
import org.prodet.repository.repository.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitConfig {
	
	@Transactional
	@Autowired
	public void init(UserRepositoryInterface userRepository, RoleRepositoryInterface roleRepository) {
		
		ArrayList<Role> janakeRoles = new ArrayList<Role>();
		ArrayList<Role> adminRoles = new ArrayList<Role>();

		roleRepository.save(new Role(1l, "admin"));
		roleRepository.save(new Role(2l, "user"));
		
		Role adminRole = roleRepository.findOne(1l);
		Role userRole = roleRepository.findOne(2l);
		adminRoles.add(adminRole);
		adminRoles.add(userRole);
		janakeRoles.add(userRole);
		
		User admin = new User("admin", "admin@example.com", 
				"pwd", "", "", true, adminRoles);	
		userRepository.save(admin);
		
		janakeRoles.add(userRole);
		userRole = roleRepository.findOne(2l);
		User janake = new User("janake", "janak.endre@gmail.com", 
				"pwd", "", "", true, janakeRoles);
		
		userRepository.save(janake);
		
	}
	
}
