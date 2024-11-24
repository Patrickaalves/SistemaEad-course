package com.ead.course.services;

import com.ead.course.dto.CourseDto;
import com.ead.course.models.CourseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;
import java.util.UUID;

public interface CourseService {
    CourseModel save(CourseDto courseDto);
    Page<CourseModel> findAll(Specification<CourseModel> spec, Pageable pageable);
    Optional<CourseModel> findById(UUID courseId);
    CourseModel update(CourseDto courseDto, CourseModel courseModel);
    void delete(CourseModel courseModel);
    Boolean existByName(String courseName);
}
