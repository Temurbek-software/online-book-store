package com.onlinebook.demo.controller;

import com.onlinebook.demo.payload.ApiResult;
import com.onlinebook.demo.payload.CategoryDTO;
import com.onlinebook.demo.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/all")
    public ApiResult<List<CategoryDTO>> getAllCategoryInfo()
    {
        return categoryService.categoryListInfo();
    }
    @GetMapping("/{id}")
    public ApiResult<CategoryDTO> getOneCategoryInfo(@PathVariable Long id)
    {
       return categoryService.getCategoryDTOById(id);
    }
    @PostMapping("/insert")
    public ApiResult<?> saveCategory(@RequestBody CategoryDTO categoryDTO)
    {
        return categoryService.insertNewCategory(categoryDTO);
    }
    @DeleteMapping("/delete/{id}")
    public ApiResult<?> deleteCategory(@PathVariable Long id)
    {
        return categoryService.deleteCategoryDTOById(id);
    }
    @PutMapping("/update/{id}")
    public ApiResult<?> updateCategory(@PathVariable Long id,
                                       @RequestBody CategoryDTO categoryDTO)
    {
        return categoryService.updateExistCategory(id,categoryDTO);
    }
}
