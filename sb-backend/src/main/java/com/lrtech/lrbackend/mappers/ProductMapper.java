package com.lrtech.lrbackend.mappers;


import com.lrtech.lrbackend.dtoModels.ProductDTO;
import com.lrtech.lrbackend.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {FilamentMapper.class})
public interface ProductMapper {

    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "version", ignore = true)
    Product productDtoToProduct(ProductDTO dto);

    ProductDTO productToProductDto(Product product);
}
