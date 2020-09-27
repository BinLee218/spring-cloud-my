package com.company.sharding.enums;

/**
 * @author bin.li
 * @date 2020/9/26
 */
public enum BookTypeEnum {

    TYPE_1(1),
    TYPE_2(2),
    TYPE_3(3),
    ;

    private int type;
    BookTypeEnum(int type) {
        this.type = type;
    }

}
