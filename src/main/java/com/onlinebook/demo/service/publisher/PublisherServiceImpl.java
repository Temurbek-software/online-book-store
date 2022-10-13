package com.onlinebook.demo.service.publisher;


import com.onlinebook.demo.entity.Publisher;
import com.onlinebook.demo.payload.ApiResult;
import com.onlinebook.demo.payload.PublisherDTO;
import com.onlinebook.demo.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;

    @Override
    public Publisher mapToPublisher(PublisherDTO publisherDTO) {
        if (publisherDTO == null) {
            return null;
        }
        Publisher publisher = new Publisher();
        publisher.setName(publisherDTO.getName());
        publisher.setAddress(publisherDTO.getAddress());
        publisher.setDescription(publisher.getDescription());
        publisher.setPhoneNumber(publisherDTO.getPhoneNumber());
        publisher.setEmail(publisherDTO.getEmail());
        publisher.setEstablished_year(publisherDTO.getEstablished_year());
        return publisher;
    }

    @Override
    public ApiResult<List<PublisherDTO>> getAllPublisher() {
        List<Publisher> publishers = publisherRepository.findAll();
        List<PublisherDTO> publisherDTOS = publishers.stream()
                .map(PublisherDTO::new)
                .collect(Collectors.toList());
        return ApiResult.successResponse(publisherDTOS);
    }

    @Override
    public ApiResult<PublisherDTO> getOnePublisher(Long id) {
        Publisher publisher = publisherRepository.getById(id);
        PublisherDTO publisherDTO= new PublisherDTO(publisher);
        return ApiResult.successResponse(publisherDTO);
    }

    @Override
    public ApiResult<String> savePublisher(PublisherDTO publisherDTO)
    {
        Publisher publisher = mapToPublisher(publisherDTO);
        publisherRepository.save(publisher);
        return ApiResult.successResponse("Successfully saved");
    }

    @Override
    public ApiResult<?> deletePublisher(Long id) {
        if (publisherRepository.getOne(id) == null) {
            return ApiResult.successResponse("Not exist");
        } else {
            publisherRepository.deleteById(id);
            return ApiResult.successResponse("Successfuly deleted");
        }
    }

    @Override
    public ApiResult<String> updatingPublisher(Long id, PublisherDTO publisherDTO) {
        Publisher optionalPublisher = publisherRepository.findById(id).get();
        Publisher publisher = mapToPublisher(publisherDTO);
        if (!(publisher == null)) {
            optionalPublisher.setName(publisher.getName());
            optionalPublisher.setDescription(publisher.getDescription());
            optionalPublisher.setAddress(publisher.getAddress());
            optionalPublisher.setEmail(publisher.getEmail());
            optionalPublisher.setEstablished_year(publisher.getEstablished_year());
            optionalPublisher.setPhoneNumber(publisher.getPhoneNumber());
            publisherRepository.save(optionalPublisher);
            return new ApiResult<>("This publisher has succesfully saved");
        } else {
            return new ApiResult<>("This publisher does not exist");
        }
    }


}
