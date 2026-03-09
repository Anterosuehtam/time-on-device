package com.project.time_on_device.service;

import com.project.time_on_device.entity.Category;
import com.project.time_on_device.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Avisa ao Spring que aqui cuidamos das regras do negócio
public class CategoryService {

    @Autowired // O Spring injeta o Repositório aqui dentro automaticamente
    private CategoryRepository categoryRepository;

    // CREATE (POST)
    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

    // READ (GET)
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    // UPDATE (PUT)
    public Category updateCategory(Integer id, Category categoryAtualizada) {
        Category categoriaExistente = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));

        if (categoryAtualizada.getName() != null) {
            categoriaExistente.setName(categoryAtualizada.getName());
        }
        if (categoryAtualizada.getIsProductive() != null) {
            categoriaExistente.setIsProductive(categoryAtualizada.getIsProductive());
        }
        return categoryRepository.save(categoriaExistente);
    }

    // DELETE
    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }

}
