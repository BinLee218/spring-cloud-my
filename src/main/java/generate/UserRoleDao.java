package generate;

import generate.UserRole;

public interface UserRoleDao {
    int deleteByPrimaryKey(Integer uRId);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer uRId);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
}