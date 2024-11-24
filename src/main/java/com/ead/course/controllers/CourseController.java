package com.ead.course.controllers;

import com.ead.course.dto.CourseDto;
import com.ead.course.models.CourseModel;
import com.ead.course.services.CourseService;
import com.ead.course.specifications.SpecificationTemplate;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/courses")
public class CourseController {

    Logger logger = LogManager.getLogger(CourseController.class);

    final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<Object> saveCourse(@RequestBody @Valid CourseDto courseDto) {
        logger.debug("POST saveCourse courseDto received {} ", courseDto);
        if (courseService.existByName(courseDto.name())) {
            logger.warn("Course Name {} is Already Taken", courseDto.name());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Course name already taken!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(courseDto));
    }

    @GetMapping
    public ResponseEntity<Page<CourseModel>> getAllCourses(SpecificationTemplate.CourseSpec spec, Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.findAll(spec, pageable));
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Object> getCourseById(@PathVariable(value = "courseId") UUID courseId) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.findById(courseId).get());
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Object> deleteCourseById(@PathVariable(value = "courseId") UUID courseId) {
        logger.debug("DELETE deleteCourse courseId : {}", courseId);
        courseService.delete(courseService.findById(courseId).get());
        return ResponseEntity.status(HttpStatus.OK).body("Course deleted successfully!");
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<Object> updateCourse(@PathVariable(value = "courseId") UUID courseId,
                                               @RequestBody @Valid CourseDto courseDto) {
        logger.debug("PUT updateCourse courseDto received {}", courseDto);
        return ResponseEntity.status(HttpStatus.OK)
                             .body(courseService.update(courseDto, courseService.findById(courseId).get()));
    }
}
