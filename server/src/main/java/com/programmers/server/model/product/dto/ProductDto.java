package com.programmers.server.model.product.dto;

import com.programmers.server.model.product.Category;
import lombok.Getter;

@Getter
public class ProductDto {

    private final String name;
    private final Category category;
    private final int price;
    private final String description;

    public ProductDto(String name, Category category, int price, String description) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
    }
}
