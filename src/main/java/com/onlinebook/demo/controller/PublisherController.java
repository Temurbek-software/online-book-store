package com.onlinebook.demo.controller;

import com.onlinebook.demo.payload.ApiResult;
import com.onlinebook.demo.payload.AuthorDTO;
import com.onlinebook.demo.payload.PublisherDTO;
import com.onlinebook.demo.service.publisher.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/publisher")
@RequiredArgsConstructor
public class PublisherController {
    private final PublisherService publisherService;

    @GetMapping()
    public ApiResult<List<PublisherDTO>> getAllPublisher() {
        ApiResult<List<PublisherDTO>> apiResult = publisherService.getAllPublisher();
        return apiResult;
    }

    @GetMapping("/{id}")
    public ApiResult<PublisherDTO> getOnPublisher(@PathVariable Long id) {
        ApiResult<PublisherDTO> dtoApiResult = publisherService.getOnePublisher(id);
        return dtoApiResult;
    }
}
