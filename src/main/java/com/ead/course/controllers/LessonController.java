package com.ead.course.controllers;

import com.ead.course.dto.LessonDto;
import com.ead.course.models.LessonModel;
import com.ead.course.services.LessonService;
import com.ead.course.services.ModuleService;
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
public class LessonController {

    Logger logger = LogManager.getLogger(LessonController.class);

    final ModuleService moduleService;
    final LessonService lessonService;

    public LessonController(ModuleService moduleService, LessonService lessonService) {
        this.moduleService = moduleService;
        this.lessonService = lessonService;
    }

    @PostMapping("/modules/{moduleId}/lessons")
    public ResponseEntity<Object> saveLesson(@PathVariable(value = "moduleId") UUID moduleId,
                                             @RequestBody @Valid LessonDto lessonDto) {
        logger.debug("POST saveLesson lessonRecordDto received {} ", lessonRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(lessonService.save(lessonDto, moduleService.findById(moduleId).get()));
    }

    @GetMapping("/modules/{moduleId}/lessons")
    public ResponseEntity<Page<LessonModel>> getAllLessons(@PathVariable(value = "moduleId") UUID moduleId,
                                                           SpecificationTemplate.LessonSpec spec,
                                                           Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(lessonService.findAllLessonsIntoModule(
                        SpecificationTemplate.lessonModuleId(moduleId).and(spec), pageable
                ));
    }

    @GetMapping("/modules/{moduleId}/lessons/{lessonId}")
    public ResponseEntity<Object> getOneLesson(@PathVariable("moduleId") UUID moduleId,
                                               @PathVariable("lessonId") UUID lessonId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(lessonService.findLessonIntoModule(moduleId, lessonId).get());
    }


    @PutMapping("/modules/{moduleId}/lessons/{lessonId}")
    public ResponseEntity<Object> update(@PathVariable("moduleId") UUID moduleId,
                                         @PathVariable("lessonId") UUID lessonId,
                                         @RequestBody @Valid LessonDto lessonDto) {
        logger.debug("PUT updateLesson lessonRecordDto received {} ", lessonDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(lessonService.update(lessonDto, lessonService.findLessonIntoModule(moduleId, lessonId).get()));
    }

    @DeleteMapping("/modules/{moduleId}/lessons/{lessonId}")
    public ResponseEntity<Object> delete(@PathVariable("moduleId") UUID moduleId,
                                         @PathVariable("lessonId") UUID lessonId) {
        logger.debug("DELETE deleteLesson lessonId received {} ", lessonId);
        lessonService.delete(lessonService.findLessonIntoModule(moduleId, lessonId).get());
        return ResponseEntity.status(HttpStatus.OK).body("Lesson deleted successfully.");
    }


}
