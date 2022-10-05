package com.onlinebook.demo.service.products;

import com.onlinebook.demo.entity.Author;
import com.onlinebook.demo.entity.Product;
import com.onlinebook.demo.payload.ApiResult;
import com.onlinebook.demo.payload.AuthorDTO;
import com.onlinebook.demo.payload.ProductDTO;

import java.util.List;

public interface ProductService {
   ApiResult<List<ProductDTO>> getAllProduct();
   ApiResult<ProductDTO> getOneItem(Long id);
   ApiResult<String> saveNewProduct(ProductDTO productDTO);
   ApiResult<?> deleteProduct(Long id);
   ApiResult<String> updateExistProduct(Long id,ProductDTO productDTO);
   ProductDTO mapToProductDTO(Product product);

}
