package com.ead.course.services.impl;

import com.ead.course.repositories.CourseRepository;
import com.ead.course.services.CourseService;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceimpl implements CourseService{
    final CourseRepository courseRepository;

    public CourseServiceimpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

}
