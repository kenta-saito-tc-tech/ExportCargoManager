package com.example.export_cargo_manager.Entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRecord(int id, @NotBlank String loginId, @NotBlank String password, @NotBlank String name, @Min(1) int responsibleId) {
}
