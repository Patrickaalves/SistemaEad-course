package com.ead.course.services;

import com.ead.course.dto.LessonDto;
import com.ead.course.models.LessonModel;
import com.ead.course.models.ModuleModel;

public interface LessonService {
    LessonModel save(LessonDto lessonDto, ModuleModel moduleModel);
}
