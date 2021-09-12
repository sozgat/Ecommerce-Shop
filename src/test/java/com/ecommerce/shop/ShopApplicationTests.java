package com.ecommerce.shop;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
// @TestPropertySource(locations = "classpath:application-integrationtest.properties")
class ShopApplicationTests {

    private final Logger logger = LoggerFactory.getLogger(ShopApplicationTests.class);

    @Autowired
    private PasswordEncoder encoder;

    @Test
    void contextLoads() {

        String encode = encoder.encode("123123");
        logger.info("Pass: {}", encode);

    }




}
