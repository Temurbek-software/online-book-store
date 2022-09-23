package com.onlinebook.demo.controller;

import com.onlinebook.demo.entity.Author;
import com.onlinebook.demo.exception.ExceptionHelper;
import com.onlinebook.demo.payload.ApiResult;
import com.onlinebook.demo.payload.AuthorDTO;
import com.onlinebook.demo.service.author.AuthorService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/author")
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/all")
        public ApiResult<List<AuthorDTO>> selectAllAuthors() {
        ApiResult<List<AuthorDTO>> apiResult = authorService.ListOfAllAuthors();
        return apiResult;
    }

    @GetMapping("/{id}")
    public ApiResult<AuthorDTO> selectOneAuthor(@PathVariable Long id) {
        ApiResult<AuthorDTO> dtoApiResult = null;
        try {
            dtoApiResult = authorService.getAuthorById(id);
        } catch (EntityNotFoundException exception) {
            exception.printStackTrace();
        }
        return dtoApiResult;

    }

    @PostMapping("/insertAuthor")
    public ApiResult<?> saveAuthor(@RequestBody AuthorDTO authorDTO) {
        ApiResult<String> saveAuthor = null;
        try {
            saveAuthor = authorService.insertNewAuthor(authorDTO);
        }
        catch (EntityNotFoundException exception)
        {
            exception.printStackTrace();
        }
        return saveAuthor;
    }

    @DeleteMapping("/delete/{id}")
    public ApiResult<?> deleteAuthorById(@PathVariable Long id) {
        ApiResult<?> del = authorService.deleteAuthorById(id);
        return del;
    }

    @PutMapping("/update/{id}")
    public ApiResult<?> updateAuthorById(@PathVariable Long id,
                                         @RequestBody AuthorDTO authorDTO) {
        ApiResult<String> status = authorService.updatingAuthorById(id, authorDTO);
        return status;
    }
}
