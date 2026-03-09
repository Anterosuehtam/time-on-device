package com.project.time_on_device.controller;

import com.project.time_on_device.entity.User;
import com.project.time_on_device.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Controlador REST (linguagem: JSON)
@RequestMapping("/users") // Endereço base na internet é "/users"
public class UserController {

    @Autowired
    private UserService userService; // Chamando o cérebro da aplicação, onde estão as regras de negócio

    // Rota para CRIAR um usuário (Verbo HTTP: POST)
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        // Manda o Service salvar e guarda a resposta
        User savedUser = userService.createUser(user);

        // Retorna o usuário salvo com o status 201 (Created)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    // Rota para BUSCAR todos os usuários (Verbo HTTP: GET)
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        // Pede a lista para o Service
        List<User> users = userService.getAllUsers();

        // Retorna a lista com o status 200 (OK)
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
        User userSalvo = userService.updateUser(id, user);
        return ResponseEntity.ok(userSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}