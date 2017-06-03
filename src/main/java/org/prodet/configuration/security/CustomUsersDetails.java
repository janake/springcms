package org.prodet.configuration.security;

import org.prodet.repository.domain.User;
import org.prodet.repository.repository.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class CustomUsersDetails extends User implements UserDetailsService {
	
	@Autowired
	private UserRepositoryInterface userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByuserNameOrEmail(username);
		return new CustomUserDetails(user);
	}
}