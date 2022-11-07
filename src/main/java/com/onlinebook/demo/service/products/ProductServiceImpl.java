package com.onlinebook.demo.service.products;

import com.onlinebook.demo.entity.*;
import com.onlinebook.demo.mapper.ServiceMapper;
import com.onlinebook.demo.payload.ApiResult;
import com.onlinebook.demo.payload.AuthorDTO;
import com.onlinebook.demo.payload.CompanyDTO;
import com.onlinebook.demo.payload.ProductDTO;
import com.onlinebook.demo.repository.*;
import com.onlinebook.demo.service.company.CompanyService;
import com.onlinebook.demo.utils.RestConstants;
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
    private final ServiceMapper serviceMapper;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final PublisherRepository publisherRepository;
    private final CompanyRepository companyRepository;


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
        productDTO.setAuthorsList(product.getProductAuthor().stream().map(
                author -> serviceMapper.mapToAuthorDTO(author)
        ).collect(Collectors.toSet()));
        productDTO.setCategory_Id(serviceMapper.mapToCategoryDTO(product.getCategory()));
        productDTO.setPublisher_Id(serviceMapper.mapToPublisherDTO(product.getProductPublisher()));
        productDTO.setCompany_Id(serviceMapper.mapToCompanyDTO(product.getProductCompany()));
        return productDTO;
    }

    @Override
    public ApiResult<List<ProductDTO>> getAllProduct() {
        List<Product> products = productRepository.getAllByProductsIfNotDeleted(false);
        List<ProductDTO> productDTOList = products.stream()
                .map(this::mapToProductDTO)
                .collect(Collectors.toList());
        return ApiResult.successResponse(productDTOList);
    }

    @Override
    public ApiResult<ProductDTO> getOneItem(Long id) {
        Product product = productRepository.getById(id);

        if (product.isDeleted())
            return ApiResult.errorResponse("This book has already deleted", RestConstants.NOT_FOUND);
        else
            return ApiResult.successResponse(mapToProductDTO(product));
    }

    @Override
    public ApiResult<String> saveNewProduct(ProductDTO productDTO) {
        Product product = serviceMapper.mapToProduct(productDTO);
        Company company = companyRepository.getById(productDTO.getCompany_Id().getId());
        Publisher publisher=publisherRepository.getById(productDTO.getPublisher_Id().getId());
        Category category= categoryRepository.getById(productDTO.getCategory_Id().getId());
        product.setProductCompany(company);
        product.setProductPublisher(publisher);
        product.setCategory(category);
        Set<Author> productList=productDTO.getAuthorsList()
                .stream().map(d->authorRepository.getById(d.getId()))
                .collect(Collectors.toSet());
        for (Author author:productList)
        {
            author.getProductDTOSet().add(product);
        }
        product.setProductAuthor(productList);
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
        Product optionalProduct = productRepository.getById(id);
        Product product2 = serviceMapper.mapToProduct(productDTO);
        if (!(optionalProduct == null))
        {
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
            return new ApiResult<>("This product has successfully saved");
        } else {
            return new ApiResult<>("This product does not ex\n" +
                    "    }ist");
        }
    }
}
