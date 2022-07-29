package com.onlinebook.demo.controller;

import com.onlinebook.demo.entity.Author;
import com.onlinebook.demo.payload.ApiResult;
import com.onlinebook.demo.payload.AuthorDTO;
import com.onlinebook.demo.service.author.AuthorService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/author")
public class AuthorController
{
    private final AuthorService authorService;

    @GetMapping("/all")
    public ApiResult<List<AuthorDTO>> getAllAuthorss() {
        ApiResult<List<AuthorDTO>> apiResult = authorService.getAllAuthors();
        return apiResult;
    }

    @GetMapping("/all/{id}")
    public ApiResult<AuthorDTO> getOnAuthor(@PathVariable Long id) {
        ApiResult<AuthorDTO> dtoApiResult = authorService.getoneAuthor(id);
        return dtoApiResult;
    }

}
