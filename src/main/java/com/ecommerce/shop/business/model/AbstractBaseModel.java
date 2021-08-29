package com.ecommerce.shop.business.model;

import com.ecommerce.shop.business.constant.ProjectConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractBaseModel implements Serializable, Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ProjectConstants.ID_COLUMN_NAME, unique = true, nullable = false, updatable = false)
    private long id = ProjectConstants.ID_UNSAVED_VALUE;

    @Column(name = ProjectConstants.VERSION_COLUMN_NAME)
    @Version()
    private long version = ProjectConstants.ID_UNSAVED_VALUE;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = ProjectConstants.ACTIVE_COLUMN_NAME, nullable = false)
    private boolean active = true;

    @CreatedBy
    @Column(name = ProjectConstants.CREATED_BY_COLUMN_NAME, updatable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(name = ProjectConstants.LAST_UPDATED_BY_COLUMN_NAME)
    private String lastUpdatedBy;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @CreatedDate
    @Column(name = ProjectConstants.CREATION_DATE_COLUMN_NAME, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @LastModifiedDate
    @Column(name = ProjectConstants.LAST_UPDATED_DATE_COLUMN_NAME)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractBaseModel that = (AbstractBaseModel) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
