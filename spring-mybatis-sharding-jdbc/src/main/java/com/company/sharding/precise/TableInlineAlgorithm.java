package com.company.sharding.precise;

import com.company.sharding.enums.ShardingTablesEnum;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;

/**
 * @author bin.li
 * @date 2020/9/24
 */
public class TableInlineAlgorithm implements PreciseShardingAlgorithm {

    @Override
    public String doSharding(Collection availableTargetNames, PreciseShardingValue shardingValue) {
        ShardingTablesEnum shardingTablesEnum = ShardingTablesEnum.getShardingTablesEnum(shardingValue.getLogicTableName());
        if (shardingValue.getColumnName().equals(Objects.requireNonNull(shardingTablesEnum).getColumnName())) {
            Integer value = Integer.parseInt(shardingValue.getValue().toString());
            LinkedHashSet<String> linkedHashSet = (LinkedHashSet<String>) availableTargetNames;
            String[] objects = linkedHashSet.toArray(new String[]{});
            String object = objects[value];
            return object;
        }
        throw new RuntimeException("NotFoundShardingTableException");
    }
}
