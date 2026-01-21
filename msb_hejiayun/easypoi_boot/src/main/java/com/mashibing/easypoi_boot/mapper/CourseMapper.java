package com.mashibing.easypoi_boot.mapper;


import com.mashibing.easypoi_boot.entity.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper {

    //查询所有课程
    List<Course> findAll();
}
