package com.lrtech.lrbackend.services.client;

import com.lrtech.lrbackend.dtoModels.ProductDTO;
import com.lrtech.lrbackend.mappers.ProductMapper;
import com.lrtech.lrbackend.models.Product;
import com.lrtech.lrbackend.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ClientProductServiceImpl implements ClientProductService {

    //Fields //
    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    private final static int DEFAULT_PAGE = 0;

    private final static int DEFAULT_PAGE_SIZE = 25;

    private final static int MAX_PAGE_SIZE = 100;

    private final Sort defaultSort = Sort.by(Sort.Order.asc("productName"));

    //Methods //

    private PageRequest buildPageRequest(Integer pageNumber, Integer pageSize){
        int reqPageNumber;
        int reqPageSize;
        if(pageNumber != null && pageNumber > 0){
            reqPageNumber = pageNumber - 1;
        }
        else {
            reqPageNumber = DEFAULT_PAGE;
        }

        if(pageSize != null && pageSize > 0){
            if(pageSize >  MAX_PAGE_SIZE){
                pageSize = MAX_PAGE_SIZE;
            }
            reqPageSize = pageSize;
        }
        else {
            reqPageSize = DEFAULT_PAGE_SIZE;
        }


        return PageRequest.of(reqPageNumber, reqPageSize, defaultSort);
    }

    @Override
    public Page<ProductDTO> productList(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);
        Page<ProductDTO> page = productRepository.findAll(pageRequest).map(productMapper::productToProductDto);
        return page;

    }

    @Override
    public Optional<ProductDTO> getProductById(UUID id) {
        return Optional.ofNullable(
                productMapper.productToProductDto(
                        productRepository.findById(id)
                                .orElse(null)));
    }

    @Override
    public boolean validateDto(ProductDTO dto) {
        Optional<Product> productOptional = productRepository.findById(dto.getId());
        if(productOptional.isPresent()){
            Product product = productOptional.get();
            if(dto.getPrice().compareTo(product.getPrice()) == 0 &&
                dto.getProductName().equals(product.getProductName())){
                return true;
            }
        }
        return false;
    }

    @Override
    public Optional<Product> retrieveProduct(UUID id) {
        return productRepository.findById(id);
    }
}
