package com.onlinebook.demo.service.category;

import com.onlinebook.demo.payload.ApiResult;
import com.onlinebook.demo.payload.CategoryDTO;

import java.util.List;

public class CategoryServiceImpl implements CategoryService{
    @Override
    public ApiResult<List<CategoryDTO>> categoryListInfo() {
        return null;
    }

    @Override
    public ApiResult<CategoryDTO> getCategoryDTOById(Long id) {
        return null;
    }

    @Override
    public ApiResult<String> insertNewCategory(CategoryDTO categoryDTO) {
        return null;
    }

    @Override
    public ApiResult<?> deleteCategoryDTOById(Long id) {
        return null;
    }

    @Override
    public ApiResult<String> updateExistCategory(Long id, CategoryDTO categoryDTO) {
        return null;
    }
}
