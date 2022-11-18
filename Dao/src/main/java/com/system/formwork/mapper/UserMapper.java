package com.system.formwork.mapper;

import com.system.formwork.entity.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User selectById(String id);

    void insertUser(User user1);
}
