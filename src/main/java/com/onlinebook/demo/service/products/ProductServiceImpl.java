package com.onlinebook.demo.service.products;

import com.onlinebook.demo.entity.*;
import com.onlinebook.demo.mapper.ServiceMapper;
import com.onlinebook.demo.payload.ApiResult;
import com.onlinebook.demo.payload.AuthorDTO;
import com.onlinebook.demo.payload.ProductDTO;
import com.onlinebook.demo.repository.AuthorRepository;
import com.onlinebook.demo.repository.CategoryRepository;
import com.onlinebook.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService
{
    private final ProductRepository productRepository;
    private final ServiceMapper serviceMapper;

    private final AuthorRepository authorRepository;

    private final CategoryRepository categoryRepository;



    @Override
    public ProductDTO mapToProductDTO(Product product) {
        if (product == null) {
            return null;
        }
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setIsbnNumber(product.getIsbnNumber());
        productDTO.setPageNumb(product.getPageNumb());
        productDTO.setLanguage(product.getLanguage());
        productDTO.setYearOfPublished(product.getYearOfPublished());
        productDTO.setPrinted_Price(product.getPrinted_Price());
        productDTO.setE_price(product.getE_price());
        productDTO.setDescription(product.getDescription());
        productDTO.setBookName(product.getBookName());
        productDTO.setAudio_price(product.getAudio_price());
        productDTO.setAuthors(product.getProductAuthor().stream().map(
                author -> serviceMapper.mapToAuthorDTO(author)
        ).collect(Collectors.toSet()));
//        productDTO.setCategory(serviceMapper.mapToCategoryDTO(product.getCategory()));
//        productDTO.setPublisher(serviceMapper.mapToPublisherDTO(product.getProductPublisher()));
//        productDTO.setCompany(serviceMapper.mapToCompanyDTO(product.getProductCompany()));
        return productDTO;
    }

    @Override
    public ApiResult<List<ProductDTO>> getAllProduct() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOList = products.stream()
                .map(this::mapToProductDTO)
                .collect(Collectors.toList());
        return ApiResult.successResponse(productDTOList);
    }
    @Override
    public ApiResult<ProductDTO> getOneItem(Long id) {
        Product product = productRepository.getById(id);
        return ApiResult.successResponse(mapToProductDTO(product));
    }
    @Override
    public ApiResult<String> saveNewProduct(ProductDTO productDTO) {
//        long idd=productDTO.getCategory().getId(  );
        Product product = serviceMapper.mapToProduct(productDTO);
        product.getProductAuthor().addAll(
                productDTO.getAuthors()
                        .stream().map(v->{
                    AuthorDTO authorDTO=authorRepository.findAuthorById(v.getId());
                })
        )
//       product.setCategory(category);
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
        Product product2 = serviceMapper.mapToProduct(productDTO);
        if (!(optionalProduct == null)) {
            optionalProduct.setBookName(product2.getBookName());
            optionalProduct.setAudio_price(product2.getAudio_price());
            optionalProduct.setProductAuthor(product2.getProductAuthor());
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
