package com.onlinebook.demo.service.products;

import com.onlinebook.demo.entity.Product;
import com.onlinebook.demo.payload.ApiResult;
import com.onlinebook.demo.payload.ProductDTO;
import com.onlinebook.demo.repository.ProductRepository;
import com.onlinebook.demo.service.roleservices.ConverterTo;
import com.onlinebook.demo.service.roleservices.ConverterToImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ConverterTo converterTo;

    @Override
    public ApiResult<List<ProductDTO>> getAllProduct() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOList = products.stream()
                .map(per -> converterTo.toProductDto(per))
                .collect(Collectors.toList());
        return ApiResult.successResponse(productDTOList);
    }

    @Override
    public ApiResult<ProductDTO> getoneItem(Long id) {
        Product product = productRepository.getById(id);
        return ApiResult.successResponse(converterTo.toProductDto(product));
    }

    @Override
    public ApiResult<String> savePro(ProductDTO productDTO)
    {
       Product product=converterTo.toProduct(productDTO);
       productRepository.save(product);
        return ApiResult.successResponse("Successfully saved");
    }

    @Override
    public ApiResult<?> deleteProduct(Long id) {
        if (productRepository.getOne(id)==null)
        {
            return ApiResult.successResponse("Not exist");
        }
        else
        {
            productRepository.deleteById(id);
            return ApiResult.successResponse("Successfuly deleted");
        }
    }
    @Override
    public ApiResult<String> updating(Long id, ProductDTO productDTO)
    {
        Product optionalProduct=productRepository.findById(id).get();
        Product product2=converterTo.toProduct(productDTO);
        if (!(optionalProduct==null))
        {
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
        }
        else
        {
            return new ApiResult<>("This product does not exist");
        }
    }
}
