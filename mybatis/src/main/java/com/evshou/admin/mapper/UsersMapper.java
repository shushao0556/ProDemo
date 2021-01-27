package com.evshou.admin.mapper;

import com.evshou.admin.entity.Users;
import com.evshou.admin.entity.UsersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UsersMapper {
    List<Users> queryAllUsers();

    int countByExample(UsersExample example);

    int deleteByExample(UsersExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    List<Users> selectByExample(UsersExample example);

    Users selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByExample(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
}