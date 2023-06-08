package com.example.export_cargo_manager.Entity;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record IdPassRecord(@NotBlank String loginId, @NotBlank String password) {
}
