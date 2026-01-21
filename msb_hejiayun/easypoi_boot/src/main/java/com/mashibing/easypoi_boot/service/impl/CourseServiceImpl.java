package com.mashibing.easypoi_boot.service.impl;

import com.mashibing.easypoi_boot.entity.Course;
import com.mashibing.easypoi_boot.mapper.CourseMapper;
import com.mashibing.easypoi_boot.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> findAll() {
        return courseMapper.findAll();
    }
}
