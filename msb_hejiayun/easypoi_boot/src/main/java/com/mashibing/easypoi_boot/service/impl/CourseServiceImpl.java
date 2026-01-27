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

    @Override
    public void save(List<Course> courses) {

        /*course -> { ... }：Lambda 表达式，course是遍历过程中当前的课程对象，大括号里是对每个课程的处理逻辑。*/
        courses.forEach(course -> {
            course.setCid(null);  //自动生成id，不需要使用Excel中的编号
            courseMapper.save(course);
        });
    }
}
