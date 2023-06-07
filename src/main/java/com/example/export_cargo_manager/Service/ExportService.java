package com.example.export_cargo_manager.Service;

import com.example.export_cargo_manager.Entity.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ExportService {
    public List<ResponsibilityRecord> findAllResponsibility();
    public UserRecord checkIdExist(String id);
    public int insertUser(UserRecord userRecord);
    public UserRecord findUser(IdPassRecord idPassRecord);
    public List<DestinationRecord> selectDestination(int id);
    public List<DestinationRecord> findAllDestination();
    public List<ListRecord> findAll(int responsibleId);
    public List<ListRecord> cargoSort(int responsibleId, int reserveNum, int destNum, String keyword);
    public List<AirplaneRecord> findAirplaneAll();
    public int airplaneUpdate(AirplaneRecord airplaneRecord);
    public int airplaneDelete(AirplaneRecord airplaneRecord);
    public int insertAirplane(AirplaneRecord airplaneRecord);
    public int insertCargo(CargoRecord cargoRecord);
    public CargoRecord findById(int id);
}
