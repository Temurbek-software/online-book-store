package com.onlinebook.demo.service.category;

import com.onlinebook.demo.entity.Category;
import com.onlinebook.demo.payload.ApiResult;
import com.onlinebook.demo.payload.CategoryDTO;

import java.util.List;

public interface CategoryService {
    ApiResult<List<CategoryDTO>> categoryListInfo();
    ApiResult<CategoryDTO> getCategoryDTOById(Long id);
    ApiResult<String> insertNewCategory(CategoryDTO categoryDTO);
    ApiResult<?> deleteCategoryDTOById(Long id);
    ApiResult<String> updateExistCategory(Long id,CategoryDTO categoryDTO);
}
