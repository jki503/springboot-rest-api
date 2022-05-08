package com.programmers.server.controller;

import com.programmers.server.model.product.Product;
import com.programmers.server.model.product.dto.ProductDto;
import com.programmers.server.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public String productsPage(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "/product/product-list";
    }

    @GetMapping("/new-product")
    public String newProductPage() {
        return "/product/new-product";
    }

    @PostMapping("/products")
    public String newProduct(ProductDto productDto) {
        productService.createProduct(
                productDto.getName(),
                productDto.getCategory(),
                productDto.getPrice(),
                productDto.getDescription());
        return "redirect:/products";
    }

}
