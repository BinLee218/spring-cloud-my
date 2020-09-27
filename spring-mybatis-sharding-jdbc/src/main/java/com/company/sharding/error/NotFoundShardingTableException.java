package com.company.sharding.error;

/**
 * @author bin.li
 * @date 2020/9/26
 */
public class NotFoundShardingTableException extends Exception {

    public NotFoundShardingTableException(String message) {
        super(message);
    }
}
