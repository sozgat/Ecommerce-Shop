package com.ecommerce.shop.business.context.listener;

import com.ecommerce.shop.business.model.Product;
import com.ecommerce.shop.business.service.ProductService;
import com.ecommerce.shop.presentation.api.controller.ProductAPIController;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
public class ProductApplicationListener implements ApplicationListener<ApplicationReadyEvent> {

    private final ProductService productService;
    private static final Logger log =  LoggerFactory.getLogger(ProductApplicationListener.class);


    public ProductApplicationListener(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        try {
            List<Product> productAll = productService.findAll();
            if (productAll.isEmpty()){
                for (int i = 0; i < 4 ; i++) {
                    Product product = new Product();
                    Faker faker = new Faker();

                    product.setUuid(UUID.randomUUID().toString());
                    product.setName(faker.book().title());
                    product.setDescription(faker.lorem().sentence(6));
                    product.setPrice((float) faker.number().randomDouble(2,300,1000));

                    productService.save(product);
                    log.info("Row was created. id = {} ", product.getId());
                }
            }

        }
        catch (Exception e){
            log.error("Application has encountered an error!", e);
        }
    }
}
