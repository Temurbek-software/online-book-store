package com.onlinebook.demo.service.author;

import com.onlinebook.demo.entity.Author;
import com.onlinebook.demo.payload.ApiResult;
import com.onlinebook.demo.payload.AuthorDTO;
import com.onlinebook.demo.payload.ProductDTO;

import java.util.List;


public interface AuthorService
{
    ApiResult<List<AuthorDTO>> ListOfAllAuthors();
    ApiResult<AuthorDTO> getAuthorById(Long id);
    ApiResult<String> insertNewAuthor(AuthorDTO authorDTO);
    ApiResult<?> deleteAuthorById(Long id);
    ApiResult<String> updatingAuthorById(Long id,AuthorDTO authorDTO);

    Author mapToAuthorDTO(AuthorDTO authorDTO);


}
