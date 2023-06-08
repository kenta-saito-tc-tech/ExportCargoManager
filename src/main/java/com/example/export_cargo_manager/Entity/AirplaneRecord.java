package com.example.export_cargo_manager.Entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record AirplaneRecord(int id, @Min(0) int prefix, @NotBlank String letterCode,@NotBlank String name,@NotBlank String country, String mon, String tue, String wed, String thu, String fri, String sat, String sun) {
}
