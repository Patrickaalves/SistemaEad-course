package com.ead.course.services;

import com.ead.course.dto.ModuleDto;
import com.ead.course.models.CourseModel;
import com.ead.course.models.ModuleModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ModuleService {
    ModuleModel save(ModuleDto moduleDto, CourseModel courseModel);
    Optional<ModuleModel> findById(UUID id);
    List<ModuleModel> findAllModulesIntoCourse(UUID courseId);
    Optional<ModuleModel> findModuleIntoCourse(UUID courseId, UUID moduleId);
    ModuleModel update(ModuleDto moduleDto, ModuleModel moduleModel);
    void delete(ModuleModel moduleModel);
}
