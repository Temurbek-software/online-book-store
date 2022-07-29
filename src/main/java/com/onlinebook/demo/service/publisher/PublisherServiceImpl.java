package com.onlinebook.demo.service.publisher;


import com.onlinebook.demo.entity.Product;
import com.onlinebook.demo.entity.Publisher;
import com.onlinebook.demo.payload.ApiResult;
import com.onlinebook.demo.payload.ProductDTO;
import com.onlinebook.demo.payload.PublisherDTO;
import com.onlinebook.demo.repository.PublisherRepository;
import com.onlinebook.demo.service.roleservices.ConverterTo;
import jdk.dynalink.linker.ConversionComparator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {
    private final ConverterTo converterTo;
    private final PublisherRepository publisherRepository;

    @Override
    public ApiResult<List<PublisherDTO>> getAllPublisher() {
        List<Publisher> publishers = publisherRepository.findAll();
        List<PublisherDTO> publisherDTOS = publishers.stream()
                .map(per -> converterTo.toPublisherDTO(per))
                .collect(Collectors.toList());
        return ApiResult.successResponse(publisherDTOS);
    }

    @Override
    public ApiResult<PublisherDTO> getonePublisher(Long id) {
        Publisher publisher = publisherRepository.getById(id);
        return ApiResult.successResponse(converterTo.toPublisherDTO(publisher));
    }

    @Override
    public ApiResult<String> savePublisher(PublisherDTO publisherDTO) {
        Publisher publisher=converterTo.toPublisher(publisherDTO);
        publisherRepository.save(publisher);
        return ApiResult.successResponse("Successfully saved");
    }

    @Override
    public ApiResult<?> deletePublisher(Long id) {
        if (publisherRepository.getOne(id)==null)
        {
            return ApiResult.successResponse("Not exist");
        }
        else
        {
            publisherRepository.deleteById(id);
            return ApiResult.successResponse("Successfuly deleted");
        }
    }

    @Override
    public ApiResult<String> updatingPublisher(Long id, PublisherDTO publisherDTO) {
        Publisher optionalPublisher=publisherRepository.findById(id).get();
        Publisher publisher=converterTo.toPublisher(publisherDTO);
        if (!(publisher==null))
        {
            optionalPublisher.setName(publisher.getName());
            optionalPublisher.setDescription(publisher.getDescription());
            optionalPublisher.setAddress(publisher.getAddress());
            optionalPublisher.setEmail(publisher.getEmail());
            optionalPublisher.setEstablished_year(publisher.getEstablished_year());
            optionalPublisher.setPhoneNumber(publisher.getPhoneNumber());
            publisherRepository.save(optionalPublisher);
            return new ApiResult<>("This publisher has succesfully saved");
        }
        else
        {
            return new ApiResult<>("This publisher does not exist");
        }
    }
}
