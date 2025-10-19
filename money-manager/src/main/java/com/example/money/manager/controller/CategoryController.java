package com.example.money.manager.controller;

import com.example.money.manager.dto.CategoryDTO;
import com.example.money.manager.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
        CategoryDTO savedCategory = categoryService.saveCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getCategories (){
        List<CategoryDTO> category = categoryService.getCategoriesForCurrentUser();
        return ResponseEntity.ok(category);
    }


    @GetMapping("/{type}")
    public ResponseEntity<List<CategoryDTO>> getCategoriesByType(@PathVariable String type){
        List<CategoryDTO> category = categoryService.getCategoriesByTypeForCurrentUser(type);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable String categoryId,
                                                      @RequestBody CategoryDTO categoryDTO){
        CategoryDTO updatedCategory = categoryService.updateCategory(categoryId, categoryDTO);
        return ResponseEntity.ok(updatedCategory);
    }
}
