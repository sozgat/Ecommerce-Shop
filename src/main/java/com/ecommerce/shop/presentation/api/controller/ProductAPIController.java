package com.ecommerce.shop.presentation.api.controller;

import com.ecommerce.shop.business.constant.MappingConstants;
import com.ecommerce.shop.presentation.api.dto.APIResponseDTO;
import com.ecommerce.shop.presentation.api.dto.model.ProductAPIResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = MappingConstants.PRODUCT_CONTROLLER_PATH)
public class ProductAPIController {
    private static final Logger log =  LoggerFactory.getLogger(ProductAPIController.class);

    @GetMapping
    public ResponseEntity<APIResponseDTO<ProductAPIResponseDTO>> products(){
        ProductAPIResponseDTO productAPIResponseDTO = new ProductAPIResponseDTO();
        productAPIResponseDTO.setName("1");
        productAPIResponseDTO.setUuid(UUID.randomUUID().toString());

        APIResponseDTO<ProductAPIResponseDTO> apiResponse = new APIResponseDTO<>(HttpStatus.OK,productAPIResponseDTO);

        //TODO: statusu kaldırınca ne oluyor, incele
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }
}
