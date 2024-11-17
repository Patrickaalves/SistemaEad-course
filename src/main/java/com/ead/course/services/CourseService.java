package com.ead.course.services;

import com.ead.course.dto.CourseDto;
import com.ead.course.models.CourseModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourseService {
    void delete(CourseModel courseModel);
    CourseModel save(CourseDto courseDto);
    Boolean existByName(String courseName);
    List<CourseModel> findAll();
    Optional<CourseModel> findById(UUID courseId);
    CourseModel update(CourseDto courseDto, CourseModel courseModel);
}
