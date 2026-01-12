package com.example.demo.dto;

import org.springframework.web.multipart.MultipartFile;
import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDto {
    @NotBlank(message = "Name is required")
    private String name;

    private MultipartFile image;

    @NotNull(message = "Price is required")
    private BigDecimal price;

    @NotNull(message = "Stock is required")
    private Integer stock;
}
