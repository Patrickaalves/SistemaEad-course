package com.ead.course.controllers;

import com.ead.course.dto.CourseDto;
import com.ead.course.models.CourseModel;
import com.ead.course.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/courses")
public class CourseController {
    final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<Object> saveCourse(@RequestBody @Valid CourseDto courseDto) {
        if (courseService.existByName(courseDto.name())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Course name already taken!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(courseDto));
    }

    @GetMapping
    public ResponseEntity<List<CourseModel>> getCourses() {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.findAll());
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Object> getCourseById(@PathVariable(value = "courseId") UUID courseId) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.findById(courseId).get());
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Object> deleteCourseById(@PathVariable(value = "courseId") UUID courseId) {
        courseService.delete(courseService.findById(courseId).get());
        return ResponseEntity.status(HttpStatus.OK).body("Course deleted successfully!");
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<Object> updateCourse(@PathVariable(value = "courseId") UUID courseId,
                                               @RequestBody @Valid CourseDto courseDto) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(courseService.update(courseDto, courseService.findById(courseId).get()));
    }
}
