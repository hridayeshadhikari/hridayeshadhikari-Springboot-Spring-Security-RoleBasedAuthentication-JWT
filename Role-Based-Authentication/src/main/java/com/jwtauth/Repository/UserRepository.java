package com.jwtauth.Repository;

import com.jwtauth.Entity.Role;
import com.jwtauth.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);
    User findByRole(Role role);
}
