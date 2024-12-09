package com.sgpo.monolithicSGP.config;

import com.sgpo.monolithicSGP.users.entities.Role;
import com.sgpo.monolithicSGP.users.entities.Users;
import com.sgpo.monolithicSGP.users.repositories.RolesRepository;
import com.sgpo.monolithicSGP.users.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private RolesRepository rolesRepository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public AdminUserConfig(RolesRepository rolesRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.rolesRepository = rolesRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        var roleAdmin =  rolesRepository.findByName(Role.Values.Admin.name());
        var userAdmin = userRepository.findByEmail("admin@gmail.com");
        userAdmin.ifPresentOrElse(
                user -> {
                    System.out.println("Admin já existe");
                },
                () -> {
                    var user = new Users();
                    user.setEmail("admin@gmail.com");
                    user.setPhone("000000000000");
                    user.setName("Admin");
                    user.setPassword(passwordEncoder.encode("123"));
                    user.setRoles(Set.of(roleAdmin));
                    userRepository.save(user);
                }
        );
    }
}
