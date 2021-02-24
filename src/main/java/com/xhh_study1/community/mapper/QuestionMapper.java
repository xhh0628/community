package com.xhh_study1.community.mapper;

import com.xhh_study1.community.dto.QuestionDTO;
import com.xhh_study1.community.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("Insert into question(title,gmt_create,gmt_modified,creator," +
            "tag,description) " +
            "values(#{title},#{gmtCreate},#{gmtModified},#{creator}," +
            "#{tag},#{description})")
    void create(Question qusetion);

    @Select("select * from question limit #{offSet},#{size}")
    List<Question> list(@Param(value ="offSet" ) Integer offSet,@Param(value ="size" )Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where creator=#{userId} limit #{offSet},#{size}")
    List<Question> listByUserId(@Param("userId")Integer userId,@Param(value ="offSet" ) Integer offSet,@Param(value ="size" )Integer size);

    @Select("select count(1) from question where creator=#{userId}")
    Integer countByUserId(@Param("userId")Integer userId);

    @Select("select * from question where id=#{id}")
    Question getById(@Param("id")Integer id);

    @Update("update question set gmt_Modified=#{gmtModified},title=#{title},description=#{description},tag=#{tag} where id=#{id}")
    void update(Question question);
}
