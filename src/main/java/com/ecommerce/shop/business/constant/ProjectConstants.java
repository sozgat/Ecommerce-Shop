package com.ecommerce.shop.business.constant;

public class ProjectConstants {
    //TODO: UUID EKLE.
    public static final long ID_UNSAVED_VALUE = 0L;
    public static final String ID_COLUMN_NAME = "record_id";
    public static final String UUID_COLUMN_NAME = "record_uuid";
    public static final String VERSION_COLUMN_NAME = "version";
    public static final String ACTIVE_COLUMN_NAME = "is_active";
    public static final String CREATED_BY_COLUMN_NAME = "created_by";
    public static final String LAST_UPDATED_BY_COLUMN_NAME = "last_updated_by";
    public static final String CREATION_DATE_COLUMN_NAME = "creation_date";
    public static final String LAST_UPDATED_DATE_COLUMN_NAME = "last_updated_date";

    public class TableConstants {
        public static final String PRODUCT_TABLE_NAME = "PRODUCT";
        public static final String USER_TABLE_NAME = "USER";

        public class Product{
            public static final String NAME = "name";
            public static final String DESCRIPTION = "description";
            public static final String PRICE = "price";
        }
    }




}
