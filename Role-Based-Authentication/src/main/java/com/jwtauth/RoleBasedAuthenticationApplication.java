package com.jwtauth;

import com.jwtauth.Entity.Role;
import com.jwtauth.Entity.User;
import com.jwtauth.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@SpringBootApplication
public class RoleBasedAuthenticationApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(RoleBasedAuthenticationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User adminAccount=userRepository.findByRole(Role.ADMIN);
        if(adminAccount==null){
            User user=new User();
            user.setEmail("harry@gmail.com");
            user.setFirstName("Harry");
            user.setLastName("Singh");
            user.setRole(Role.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("hridayesh"));
            userRepository.save(user);
        }
    }
}
