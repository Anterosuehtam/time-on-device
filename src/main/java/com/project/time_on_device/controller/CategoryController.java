package com.project.time_on_device.controller;

import com.project.time_on_device.entity.Category;
import com.project.time_on_device.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // Controlador REST (linguagem: JSON)
@RequestMapping("/categories") // Endereço base na internet é "/categories"
public class CategoryController {

    @Autowired
    private CategoryService categoryService; // Chamando o cérebro, onde estão as regras de negócio

    //Rota para CRIAR uma categoria
    @PostMapping
    public ResponseEntity<Category> createCategory (@RequestBody Category category) {
        // Manda o Service salvar e guarda a resposta
        Category categorySaved = categoryService.createCategory(category);

        // Retorna a categoria salva com o status 201 (Created)
        return ResponseEntity.status(HttpStatus.CREATED).body(categorySaved);
    }

    //Rota para BUSCAR todas as categorias
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        // Pede a lista para o Service
        List<Category> categories = categoryService.getAllCategories();

        // Retorna a lista com o status 200 (OK)
        return ResponseEntity.ok(categories);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Integer id, @RequestBody Category category) {
        Category categoriaSalva = categoryService.updateCategory(id, category);
        return ResponseEntity.ok(categoriaSalva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id){
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();

    }

}
