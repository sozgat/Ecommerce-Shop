package com.ecommerce.shop.presentation.api.dto.model;

public abstract class AbstractAPIModelDTO {

    private String uuid;
    private long version;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
