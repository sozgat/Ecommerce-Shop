package com.ecommerce.shop.business.model;

import com.ecommerce.shop.business.constant.ProjectConstants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = ProjectConstants.TableConstants.PRODUCT_TABLE_NAME)
public class Product extends AbstractBaseModel {

    @Column(name = ProjectConstants.UUID_COLUMN_NAME, length = 36, unique = true, nullable = false, updatable = false)
    private String uuid;

    @Column(name = ProjectConstants.TableConstants.Product.NAME)
    private String name;

    @Column(name = ProjectConstants.TableConstants.Product.DESCRIPTION)
    private String description;

    @Column(name = ProjectConstants.TableConstants.Product.PRICE)
    private String price;

}
