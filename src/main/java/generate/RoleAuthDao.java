package generate;

import generate.RoleAuth;

public interface RoleAuthDao {
    int deleteByPrimaryKey(Integer rAId);

    int insert(RoleAuth record);

    int insertSelective(RoleAuth record);

    RoleAuth selectByPrimaryKey(Integer rAId);

    int updateByPrimaryKeySelective(RoleAuth record);

    int updateByPrimaryKey(RoleAuth record);
}