package com.onlinebook.demo.controller;

import com.onlinebook.demo.payload.ApiResult;
import com.onlinebook.demo.payload.ProductDTO;
import com.onlinebook.demo.service.products.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class ProductController
{
    private final ProductService productService;

    @GetMapping("/all")
    public ApiResult<List<ProductDTO>> getAllProducts()
    {
        ApiResult<List<ProductDTO>> result = productService.getAllProduct();
        return result;
    }

    @GetMapping("/{id}")
    public ApiResult<ProductDTO> getOneItem(@PathVariable Long id) {
        ApiResult<ProductDTO> result = productService.getOneItem(id);
        return result;
    }

    @PostMapping("/save")
    public ApiResult<String> saveProduct(@RequestBody ProductDTO productDTO)
    {
        ApiResult<String> apiResult = productService.saveNewProduct(productDTO);
        return apiResult;
    }

    @DeleteMapping("/delete/{id}")
    public ApiResult<?> deleteProduct(@PathVariable Long id) {
        ApiResult<?> apiResult2 = productService.deleteProduct(id);
        return apiResult2;
    }

    @PutMapping("/update/{id}")
    public ApiResult<String> updateProduct(@PathVariable Long id,
                                           @RequestBody ProductDTO productDTO) {
        ApiResult<String> apiResult = productService.updateExistProduct(id, productDTO);
        return apiResult;
    }
}
