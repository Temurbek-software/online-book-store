package com.onlinebook.demo.service.products;

import com.onlinebook.demo.entity.Product;
import com.onlinebook.demo.payload.ApiResult;
import com.onlinebook.demo.payload.ProductDTO;

import java.util.List;

public interface ProductService {
   ApiResult<List<ProductDTO>> getAllProduct();
   ApiResult<ProductDTO> getoneItem(Long id);
   ApiResult<String> savePro(ProductDTO productDTO);
   ApiResult<?> deleteProduct(Long id);
   ApiResult<String> updating(Long id,ProductDTO productDTO);
}
