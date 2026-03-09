package com.project.time_on_device.service;

import com.project.time_on_device.entity.User;
import com.project.time_on_device.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Avisa ao Spring que aqui cuidamos das regras do negócio
public class UserService {

    @Autowired // O Spring injeta o Repositório aqui dentro automaticamente
    private UserRepository userRepository;

    // CREATE (POST)
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // READ (GET)
    public List<User> getAllUsers() {
        return userRepository.findAll(); // Faz um "SELECT * FROM User" automático
    }

    // UPDATE (PUT)
    public User updateUser(Integer id, User userAtualizado) {
        User userExistente = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        if (userAtualizado.getName() != null) {
            userExistente.setName(userAtualizado.getName());
        }
        if (userAtualizado.getEmail() != null) {
            userExistente.setEmail(userAtualizado.getEmail());
        }
        if (userAtualizado.getDailyLimitMinutes() != null) {
            userExistente.setDailyLimitMinutes(userAtualizado.getDailyLimitMinutes());
        }
        return userRepository.save(userExistente);
    }

    // DELETE
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}