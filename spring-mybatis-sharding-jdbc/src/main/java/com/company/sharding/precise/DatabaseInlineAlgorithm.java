package com.company.sharding.precise;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;

/**
 * @author bin.li
 * @date 2020/9/24
 * 分库的自定义逻辑
 *
 */
public class DatabaseInlineAlgorithm implements PreciseShardingAlgorithm {

    @Override
    public String doSharding(Collection availableTargetNames, PreciseShardingValue shardingValue) {
        if (shardingValue.getLogicTableName().equals("book_info")) {
            if(shardingValue.getColumnName().equals("book_type")){
                Integer value = Integer.parseInt(shardingValue.getValue().toString());
                LinkedHashSet<String> linkedHashSet = (LinkedHashSet<String>) availableTargetNames;
                String[] objects = linkedHashSet.toArray(new String[]{});
                String object = objects[value];
                return object;
            }
        }
        return null;
    }
}
