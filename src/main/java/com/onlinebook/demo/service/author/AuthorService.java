package com.onlinebook.demo.service.author;

import com.onlinebook.demo.entity.Author;
import com.onlinebook.demo.payload.ApiResult;
import com.onlinebook.demo.payload.AuthorDTO;
import com.onlinebook.demo.payload.ProductDTO;

import java.util.List;


public interface AuthorService
{
    ApiResult<List<AuthorDTO>> getAllAuthors();
    ApiResult<AuthorDTO> getoneAuthor(Long id);
    ApiResult<String> saveAuthor(AuthorDTO authorDTO);
    ApiResult<?> deleteAuthor(Long id);
    ApiResult<String> updatingAuhtor(Long id,AuthorDTO authorDTO);
}
