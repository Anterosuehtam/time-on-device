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
        // Pergunta ao banco se esse e-mail já tem dono
        if (userRepository.existsByEmail(user.getEmail())) {
            // Se for verdadeiro (já existe), estouramos um erro e bloqueamos o processo!
            throw new IllegalArgumentException("Bloqueado: O e-mail " + user.getEmail() + " já está em uso!");
        }
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
            // Verifica se o e-mail que chegou é diferente do e-mail que já está no banco
            if (!userAtualizado.getEmail().equals(userExistente.getEmail())) {
                // Se for diferente, vamos no banco ver se já tem dono
                if (userRepository.existsByEmail(userAtualizado.getEmail())) {
                    throw new IllegalArgumentException("Bloqueado: O e-mail " + userAtualizado.getEmail() + " já está em uso por outra pessoa!");
                }
            }
            // Se passar por todas as barreiras, atualiza o e-mail!
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