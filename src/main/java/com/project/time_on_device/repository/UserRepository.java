package com.project.time_on_device.repository;

import com.project.time_on_device.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Devolve 'true' se o e-mail já existir no banco, e 'false' se estiver livre
    boolean existsByEmail(String email);
}
