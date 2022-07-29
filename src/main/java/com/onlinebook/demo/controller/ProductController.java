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
    public ApiResult<List<ProductDTO>> getAllProducts() {
        ApiResult<List<ProductDTO>> result = productService.getAllProduct();
        return result;
    }

    @GetMapping("/{id}")
    public ApiResult<ProductDTO> getoneItem(@PathVariable Long id) {
        ApiResult<ProductDTO> resul = productService.getoneItem(id);
        return resul;
    }

    @PostMapping("/save")
    public ApiResult<String> saveProduct(@RequestBody ProductDTO productDTO)
    {
        ApiResult<String> apiResult=productService.savePro(productDTO);
        return apiResult;
    }

    @DeleteMapping("/delete/{id}")
    public ApiResult<?> deletePorduct(@PathVariable Long id)
    {
        ApiResult<?> apiResult2=productService.deleteProduct(id);
        return apiResult2;
    }

    @PutMapping("/changing/{id}")
    public ApiResult<String> changinProduct(@PathVariable Long id,@RequestBody ProductDTO productDTO)
    {
        ApiResult<String> apiResult=productService.updating(id,productDTO);
        return apiResult;
    }
}
