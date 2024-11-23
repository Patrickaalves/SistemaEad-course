package com.ead.course.dto;

import jakarta.validation.constraints.NotBlank;

public record LessonDto(@NotBlank(message = "Title is mandatory")
                        String title,

                        @NotBlank(message = "Description is mandatory")
                        String description,

                        @NotBlank(message = "VideoUrl is mandatory")
                        String videoUrl) {
}
