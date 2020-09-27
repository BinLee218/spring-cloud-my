package com.company.sharding.enums;

/**
 * @author bin.li
 * @date 2020/9/26
 */
public enum ShardingTablesEnum {

    TABLE_BOOK_USER("book_user", "system_user"),
    ;

    private String tableName;
    private String columnName;

    ShardingTablesEnum(String tableName, String columnName) {
        this.tableName = tableName;
        this.columnName = columnName;
    }

    public static ShardingTablesEnum getShardingTablesEnum(String tableName){
        for (ShardingTablesEnum value : ShardingTablesEnum.values()) {
            if (value.getTableName().equals(tableName)) {
                return value;
            }
        }
        return null;
    }

    public String getTableName() {
        return tableName;
    }

    public String getColumnName() {
        return columnName;
    }
}
