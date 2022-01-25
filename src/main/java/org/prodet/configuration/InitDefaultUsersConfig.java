package org.prodet.configuration;

import org.prodet.repository.dao.Role;
import org.prodet.repository.dao.Type;
import org.prodet.repository.dao.User;
import org.prodet.repository.repository.RoleRepositoryInterface;
import org.prodet.repository.repository.TypeRepositoryInterface;
import org.prodet.repository.repository.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@Configuration
public class InitDefaultUsersConfig {

    @Value("#{${base.types}}")
    private Map<String, String> types;

    @Transactional
    @Autowired
    public void init(UserRepositoryInterface userRepository, RoleRepositoryInterface roleRepository,
                     TypeRepositoryInterface typeRepository) {

        if (typeRepository.count() > 0) {
            return;
        }

        ArrayList<Role> userRoles = new ArrayList<Role>();
        ArrayList<Role> adminRoles = new ArrayList<Role>();

        roleRepository.save(new Role(1L, "admin"));
        roleRepository.save(new Role(2L, "user"));

        Optional<Role> adminRole = roleRepository.findById(1l);
        Optional<Role> userRole = roleRepository.findById(2l);

        if (adminRole.isPresent()) {
            adminRoles.add(adminRole.get());
        }
        if (userRole.isPresent()) {
            adminRoles.add(userRole.get());
            userRoles.add(userRole.get());
        }

        User admin = new User("admin", "admin@example.com",
                "pwd", "", "", true, adminRoles);
        userRepository.save(admin);

        if (userRole.isPresent()) {
            userRoles.add(userRole.get());
        }

        User testUser = new User("testUser", "testuser@testmail.test",
                "pwd", "", "", true, userRoles);

        userRepository.save(testUser);

        types.entrySet().stream().forEach((e) -> {
            typeRepository.save(
                    new Type(e.getKey(), e.getValue(), testUser)
            );
        });
    }
}