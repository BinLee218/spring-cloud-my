package generate;

import generate.Applications;

public interface ApplicationsDao {
    int deleteByPrimaryKey(Integer appId);

    int insert(Applications record);

    int insertSelective(Applications record);

    Applications selectByPrimaryKey(Integer appId);

    int updateByPrimaryKeySelective(Applications record);

    int updateByPrimaryKey(Applications record);
}