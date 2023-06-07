package com.example.export_cargo_manager.Entity;

import java.sql.Timestamp;

public record CargoRecord(int id, String name, int destinationId,
                          String closeDate, String arrivalDate, int lithium,
                          int height, int width, int weight, int depth, String description,
                          int reserveStatus, Timestamp createdAt, Timestamp updatedAt) {
}
