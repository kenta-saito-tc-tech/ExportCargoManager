package com.example.export_cargo_manager.DAO;

import com.example.export_cargo_manager.Entity.*;

import java.util.List;

public interface ExportDao {
    public List<ResponsibilityRecord> findAllResponsibility();
    public UserRecord checkIdExist(String id);
    public int insertUser(UserRecord userRecord);
    public UserRecord findUser(IdPassRecord idPassRecord);
    public List<DestinationRecord> findAllDestination(int id);
    public List<ListRecord> findAll(int responsibleId);
    public List<ListRecord> cargoSort(int responsibleId, int reserveNum, int destNum, String keyword);
}
