package com.onlinebook.demo.service.publisher;

import com.onlinebook.demo.entity.Publisher;
import com.onlinebook.demo.payload.ApiResult;
import com.onlinebook.demo.payload.ProductDTO;
import com.onlinebook.demo.payload.PublisherDTO;

import java.util.List;

public interface PublisherService {
    ApiResult<List<PublisherDTO>> getAllPublisher();
    ApiResult<PublisherDTO> getOnePublisher(Long id);
    ApiResult<String> savePublisher(PublisherDTO publisherDTO);
    ApiResult<?> deletePublisher(Long id);
    ApiResult<String> updatingPublisher(Long id,PublisherDTO publisherDTO);
    Publisher mapToPublisher(PublisherDTO publisherDTO);
    PublisherDTO mapToPublisherDTO(Publisher publisher);
}
