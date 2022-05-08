package com.programmers.server.service.product;


import com.programmers.server.model.product.Category;
import com.programmers.server.model.product.Product;

import java.util.List;

public interface ProductService {

  List<Product> getProductsByCategory(Category category);

  List<Product> getAllProducts();

  Product createProduct(String productName, Category category, int price);

  Product createProduct(String productName, Category category, int price, String description);

}
