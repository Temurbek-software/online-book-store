package com.onlinebook.demo.controller;

import com.onlinebook.demo.payload.ApiResult;
import com.onlinebook.demo.payload.AuthorDTO;
import com.onlinebook.demo.payload.PublisherDTO;
import com.onlinebook.demo.service.publisher.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publisher")
@RequiredArgsConstructor
public class PublisherController {
    private final PublisherService publisherService;

    @GetMapping()
    public ApiResult<List<PublisherDTO>> getAllPublisher() {
        return publisherService.getAllPublisher();
    }

    @GetMapping("/{id}")
    public ApiResult<PublisherDTO> getOnPublisher(@PathVariable Long id) {
        return publisherService.getOnePublisher(id);
    }
    @PostMapping("/insert")
    public ApiResult<String> savePublisher(@RequestBody PublisherDTO publisherDTO)
    {
        return publisherService.savePublisher(publisherDTO);
    }
    @PutMapping("/update/{id}")
    public ApiResult<?> updatePublisherWithId(@RequestBody PublisherDTO publisherDTO,@PathVariable Long id)
    {
        return publisherService.updatingPublisher(id,publisherDTO);
    }
    @DeleteMapping("/delete/{id}")
    public ApiResult<?> deletePublisherWithId(@PathVariable Long id)
    {
        return publisherService.deletePublisher(id);
    }
}
