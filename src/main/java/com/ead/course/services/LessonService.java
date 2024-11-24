package com.ead.course.services;

import com.ead.course.dto.LessonDto;
import com.ead.course.models.LessonModel;
import com.ead.course.models.ModuleModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LessonService {
    LessonModel save(LessonDto lessonDto, ModuleModel moduleModel);
    List<LessonModel> findAllLessonsIntoModule(UUID moduleId);
    Page<LessonModel> findAllLessonsIntoModule(Specification<LessonModel> spec, Pageable pageable);
    Optional<LessonModel> findLessonIntoModule(UUID moduleId, UUID lessonId);
    LessonModel update(LessonDto lessonDto, LessonModel lessonModel);
    void delete(LessonModel lessonModel);
}
