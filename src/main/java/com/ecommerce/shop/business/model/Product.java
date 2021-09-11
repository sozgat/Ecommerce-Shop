package com.ecommerce.shop.business.model;

import com.ecommerce.shop.business.constant.ProjectConstants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = ProjectConstants.TableConstants.PRODUCT_TABLE_NAME)
public class Product extends AbstractBaseModel {

    //Test
    @Column(name = ProjectConstants.UUID_COLUMN_NAME, length = 36, unique = true, nullable = false, updatable = false)
    private String uuid;

    @Column(name = ProjectConstants.TableConstants.Product.NAME)
    private String name;

    @Column(name = ProjectConstants.TableConstants.Product.DESCRIPTION)
    private String description;

    @Column(name = ProjectConstants.TableConstants.Product.PRICE)
    private float price;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
