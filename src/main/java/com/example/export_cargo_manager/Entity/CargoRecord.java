package com.example.export_cargo_manager.Entity;

import java.sql.Timestamp;

public record CargoRecord(int id, String name, int destinationId,
                          String closeDate, String arrivalDate, int lithium,
                          int height, int width,int depth, int weight, String description,
                          int reserveStatus, Timestamp createdAt, Timestamp updatedAt) {
}
