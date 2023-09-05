package com.lrtech.lrbackend.controllers;

import com.lrtech.lrbackend.dtoModels.ProductDTO;
import com.lrtech.lrbackend.services.client.ClientProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
//@RequestMapping("/api/product")
@RestController
public class ClientProductController {
    // Fields //
    public static final String CLIENT_PRODUCT_PATH = "/api/product";

    public static final String PRODUCT_ID_PARAM = "productId";

    public static final String CLIENT_PRODUCT_ID_PATH = CLIENT_PRODUCT_PATH + "/{" + PRODUCT_ID_PARAM + "}";

    private final ClientProductService clientProductService;

    // Constructors //
    public ClientProductController(ClientProductService clientProductService) {
        this.clientProductService = clientProductService;
    }

    // Methods //

    @GetMapping(CLIENT_PRODUCT_PATH)
    public Page<ProductDTO> getProductList(
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize
    ){
        return clientProductService.productList(pageNumber, pageSize);
    }

    @GetMapping(CLIENT_PRODUCT_ID_PATH)
    public ProductDTO getProductById(@PathVariable(PRODUCT_ID_PARAM) UUID productId){
        return clientProductService.getProductById(productId)
                .orElseThrow(NotFoundRequestException::new);
    }


}
