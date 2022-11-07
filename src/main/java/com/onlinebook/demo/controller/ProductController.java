package com.onlinebook.demo.controller;

import com.onlinebook.demo.payload.ApiResult;
import com.onlinebook.demo.payload.ProductDTO;
import com.onlinebook.demo.service.products.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController
{
    private final ProductService productService;

    @GetMapping("/all")
    public ApiResult<List<ProductDTO>> getAllProducts()
    {
        return productService.getAllProduct();
    }

    @GetMapping("/{id}")
    public ApiResult<ProductDTO> getOneItem(@PathVariable Long id) {
        return productService.getOneItem(id);
    }

    @PostMapping("/save")
    public ApiResult<String> saveProduct(@Valid @RequestBody ProductDTO productDTO)
    {
        return productService.saveNewProduct(productDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResult<?> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }

    @PutMapping("/update/{id}")
    public ApiResult<String> updateProduct(@PathVariable Long id,
                                           @RequestBody ProductDTO productDTO) {
        return productService.updateExistProduct(id, productDTO);
    }
}
