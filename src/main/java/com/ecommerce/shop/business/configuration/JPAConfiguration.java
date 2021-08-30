package com.ecommerce.shop.business.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.ecommerce.shop.business.repository"})
public class JPAConfiguration {
}
