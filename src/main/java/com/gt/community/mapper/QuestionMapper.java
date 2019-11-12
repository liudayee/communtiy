package com.gt.community.mapper;

import com.gt.community.model.Quesstion;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Quesstion quesstion);

    @Select("select * from question limit #{offset},#{size}")
    List<Quesstion> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select count(1) from question where creator=#{id}")
    Integer countById(@Param(value = "id")Integer id);

    @Select("select * from question where creator=#{userId} limit #{offset},#{size}")
    List<Quesstion> listById(@Param(value = "userId")Integer userId, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select * from question where id=#{id}")
    Quesstion getById(@Param(value = "id")Integer id);

}
