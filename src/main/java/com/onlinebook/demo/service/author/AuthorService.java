package com.onlinebook.demo.service.author;

import com.onlinebook.demo.entity.Author;
import com.onlinebook.demo.payload.ApiResult;
import com.onlinebook.demo.payload.AuthorDTO;
import com.onlinebook.demo.payload.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


public interface AuthorService
{
    ApiResult<List<AuthorDTO>> ListOfAllAuthors();
    ApiResult<AuthorDTO> getAuthorById(Long id);
    ApiResult<String> insertNewAuthor(AuthorDTO authorDTO);
    ApiResult<?> deleteAuthorById(Long id);
    ApiResult<?> updatingAuthorById(Long id,AuthorDTO authorDTO);


}
