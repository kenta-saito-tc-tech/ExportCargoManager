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
    public List<DestinationRecord> findAllDestination(int id) {
        return exportDao.findAllDestination(id);
    }

    @Override
    public List<ListRecord> findAll(int responsibleId) {
        return exportDao.findAll(responsibleId);
    }

    @Override
    public List<ListRecord> cargoSort(int responsibleId, int reserveNum, int destNum, String keyword) {
        return exportDao.cargoSort(responsibleId, reserveNum, destNum, keyword);
    }


}
