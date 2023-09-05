package com.lrtech.lrbackend.services.client;

import com.lrtech.lrbackend.dtoModels.ProductDTO;
import com.lrtech.lrbackend.models.Product;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface ClientProductService {
    Page<ProductDTO> productList(Integer pageNumber, Integer pageSize);

    Optional<ProductDTO> getProductById(UUID id);

    boolean validateDto(ProductDTO dto);

    Optional<Product> retrieveProduct(UUID id);
}
