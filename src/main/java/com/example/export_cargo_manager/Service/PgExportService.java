package com.example.export_cargo_manager.Service;

import com.example.export_cargo_manager.DAO.ExportDao;
import com.example.export_cargo_manager.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PgExportService implements ExportService {
    @Autowired
    ExportDao exportDao;


    @Override
    public List<ResponsibilityRecord> findAllResponsibility() {
        return exportDao.findAllResponsibility();
    }

    @Override
    public UserRecord checkIdExist(String id) {
        return exportDao.checkIdExist(id);
    }

    @Override
    public int insertUser(UserRecord userRecord) {
        return exportDao.insertUser(userRecord);
    }

    @Override
    public UserRecord findUser(IdPassRecord idPassRecord) {
        return exportDao.findUser(idPassRecord);
    }

    @Override
    public List<DestinationRecord> selectDestination(int id) {
        return exportDao.selectDestination(id);
    }

    @Override
    public List<DestinationRecord> findAllDestination() {
        return exportDao.findAllDestination();
    }


    @Override
    public List<ListRecord> findAll(int responsibleId) {
        return exportDao.findAll(responsibleId);
    }

    @Override
    public List<ListRecord> cargoSort(int responsibleId, int reserveNum, int destNum, String keyword) {
        return exportDao.cargoSort(responsibleId, reserveNum, destNum, keyword);
    }

    @Override
    public List<AirplaneRecord> findAirplaneAll() {
        return exportDao.findAirplaneAll();
    }

    @Override
    public int airplaneUpdate(AirplaneRecord airplaneRecord) {
        return exportDao.airplaneUpdate(airplaneRecord);
    }

    @Override
    public int airplaneDelete(AirplaneRecord airplaneRecord) {
        return exportDao.airplaneDelete(airplaneRecord);
    }

    @Override
    public int insertAirplane(AirplaneRecord airplaneRecord) {
        return exportDao.insertAirplane(airplaneRecord);
    }

    @Override
    public int insertCargo(CargoRecord cargoRecord) {
        return exportDao.insertCargo(cargoRecord);
    }

    @Override
    public CargoRecord findById(int id) {
        return exportDao.findById(id);
    }

    @Override
    public int updateCargo(CargoRecord cargoRecord) {
        return exportDao.updateCargo(cargoRecord);
    }

    @Override
    public int deleteCargo(CargoRecord cargoRecord) {
        return exportDao.deleteCargo(cargoRecord);
    }


}
