package com.example.quartz.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface QuartzMapper {

    @Insert("insert into number_table values(${random}, now()")
    boolean insertData(@Param("random") int randomNumber);
}
