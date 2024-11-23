package com.ead.course.dto;

import jakarta.validation.constraints.NotBlank;

public record ModuleDto(@NotBlank(message = "Title is mandatory")
                        String title,

                        @NotBlank(message = "Description is mandatory")
                        String description) {
}
