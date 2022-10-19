package com.onlinebook.demo.service.category;

import com.onlinebook.demo.entity.Category;
import com.onlinebook.demo.exception.RestException;
import com.onlinebook.demo.mapper.ServiceMapper;
import com.onlinebook.demo.payload.ApiResult;
import com.onlinebook.demo.payload.CategoryDTO;
import com.onlinebook.demo.repository.CategoryRepository;
import com.onlinebook.demo.service.MessageService;
import com.onlinebook.demo.utils.RestConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ServiceMapper serviceMapper;

    @Override
    public ApiResult<List<CategoryDTO>> categoryListInfo() {
        List<Category> categories = categoryRepository.getAllByCategoryIfNotDeleted(false);
        List<CategoryDTO> categoryDTOList = categories.stream()
                .map(serviceMapper::mapToCategoryDTO).collect(Collectors.toList());
        return ApiResult.successResponse(categoryDTOList);
    }

    @Override
    public ApiResult<CategoryDTO> getCategoryDTOById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);

        if (category.isPresent())
            return ApiResult.successResponse(serviceMapper.mapToCategoryDTO(category.get()));
        else
            return ApiResult.errorResponse("Does not exist", RestConstants.NOT_FOUND);
    }

    @Override
    public ApiResult<String> insertNewCategory(CategoryDTO categoryDTO) {
        Category category = serviceMapper.mapToCategory(categoryDTO);
        categoryRepository.save(category);
        return ApiResult.successResponse("Saved successfully");
    }

    @Override
    public ApiResult<?> deleteCategoryDTOById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> RestException
                        .restThrow("category", "id", id,
                                MessageService.getMessage("Category not found")));
        category.setDeleted(true);
        categoryRepository.save(category);
        return ApiResult.successResponse("Successfully deleted");
    }

    @Override
    public ApiResult<?> updateExistCategory(Long id, CategoryDTO categoryDTO) {
        Optional<Category> categoryDTO1 = categoryRepository.findById(id);
        if (categoryDTO1.isPresent()) {
          Category category= categoryRepository.getById(id);
          category.setName(categoryDTO.getName());
          category.setDescription(categoryDTO.getDescription());
        } else {
            throw RestException.restThrow("not found", "category",
                    id, MessageService.getMessage("NOT_FOUND_CATEGORY"));
        }
        return ApiResult.successResponse(categoryDTO,"category has changed");
    }
}
