package com.fleabagsolutions.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fleabagsolutions.productservice.dto.ProductRequest;
import com.fleabagsolutions.productservice.dto.ProductResponse;
import com.fleabagsolutions.productservice.model.Product;
import com.fleabagsolutions.productservice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
 * This class contains business logic.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
  
  private final ProductRepository productRepository;

  public void createProduct(ProductRequest productRequest) {
    Product product = Product.builder()
            .name(productRequest.getName())
            .description(productRequest.getDescription())
            .price(productRequest.getPrice())
            .build();

    productRepository.save(product);
    //Logging - record information about the execution of a program
    log.info("Product {} is saved", product.getId());
  }

  public List<ProductResponse> getAllProducts() {
    List<Product> products = productRepository.findAll();
    
    return products.stream().map(this::mapToProductResponse).toList();
  }

  private ProductResponse mapToProductResponse(Product product) {
    return ProductResponse.builder()
              .id(product.getId())
              .name(product.getName())
              .description(product.getDescription())
              .price(product.getPrice())
              .build();
  }
}
