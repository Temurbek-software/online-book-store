package com.onlinebook.demo.service.products;

import com.onlinebook.demo.entity.*;
import com.onlinebook.demo.payload.ApiResult;
import com.onlinebook.demo.payload.AuthorDTO;
import com.onlinebook.demo.payload.CategoryDTO;
import com.onlinebook.demo.payload.ProductDTO;
import com.onlinebook.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product mapToProduct(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }
        return new Product(
                productDTO.getBookName(),
                productDTO.getE_price(),
                productDTO.getPrinted_Price(),
                productDTO.getAudio_price(),
                productDTO.getYearOfPublished(),
                productDTO.getPageNumb(),
                productDTO.getDescription(),
                productDTO.getLanguage(),
                productDTO.getIsbnNumber(),
                new Category(productDTO.getCategory().getId()),
                new Company(productDTO.getCategory().getId()),
                getAuthorDTOS(productDTO.getAuthors()),
                new Publisher(productDTO.getPublisher().getId()));
    }

    public Set<Author> getAuthorDTOS(Set<AuthorDTO> authorDTOS) {
        Set<Author> authors = new HashSet<>();
        for (AuthorDTO authorDTO : authorDTOS) {
            Author author = new Author(authorDTO.getId());
            authors.add(author);
        }
        return authors;
    }

    @Override
    public ApiResult<List<ProductDTO>> getAllProduct() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOList = products.stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
        return ApiResult.successResponse(productDTOList);
    }


    @Override
    public ApiResult<ProductDTO> getOneItem(Long id) {
        Product product = productRepository.getById(id);
        ProductDTO productDTO = new ProductDTO(product);
        return ApiResult.successResponse(productDTO);
    }

    @Override
    public ApiResult<String> saveNewProduct(ProductDTO productDTO) {
        Product product = mapToProduct(productDTO);
        productRepository.save(product);
        return ApiResult.successResponse("Successfully saved");
    }

    @Override
    public ApiResult<?> deleteProduct(Long id) {
        if (productRepository.getOne(id) == null) {
            return ApiResult.successResponse("Not exist");
        } else {
            productRepository.deleteById(id);
            return ApiResult.successResponse("Successfuly deleted");
        }
    }

    @Override
    public ApiResult<String> updateExistProduct(Long id, ProductDTO productDTO) {
        Product optionalProduct = productRepository.findById(id).get();
        Product product2 = mapToProduct(productDTO);
        if (!(optionalProduct == null)) {
            optionalProduct.setBookName(product2.getBookName());
            optionalProduct.setAudio_price(product2.getAudio_price());
            optionalProduct.setProductAuthors(product2.getProductAuthors());
            optionalProduct.setCategory(product2.getCategory());
            optionalProduct.setE_price(product2.getE_price());
            optionalProduct.setDescription(product2.getDescription());
            optionalProduct.setIsbnNumber(product2.getIsbnNumber());
            optionalProduct.setLanguage(product2.getLanguage());
            optionalProduct.setPrinted_Price(product2.getPrinted_Price());
            productRepository.save(optionalProduct);
            return new ApiResult<>("This product has succesfully saved");
        } else {
            return new ApiResult<>("This product does not ex\n" +
                    "    }ist");
        }
    }
}
