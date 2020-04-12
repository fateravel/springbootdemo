package com.example.mapper;

import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author pengsong
 * @date 2020/4/12 3:09 下午
 */
@Mapper
public interface UserMapper {

    //@Select("select * from user where id = #{id}")
    User findById(@Param("id") long id);
}
