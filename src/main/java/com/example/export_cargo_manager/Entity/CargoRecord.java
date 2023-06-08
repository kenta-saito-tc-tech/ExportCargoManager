package com.example.export_cargo_manager.Entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.sql.Timestamp;

public record CargoRecord(int id, @NotBlank String name,@Min(1) int destinationId,
                          @NotBlank String closeDate,@NotBlank String arrivalDate,@Min(1) int lithium,
                          @Min(0) int height,@Min(0) int width,@Min(0) int depth,@Min(0) int weight, String description,
                          int reserveStatus, Timestamp createdAt, Timestamp updatedAt) {
}
