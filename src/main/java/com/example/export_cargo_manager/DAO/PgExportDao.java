package com.example.export_cargo_manager.DAO;

import com.example.export_cargo_manager.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PgExportDao implements ExportDao {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    /**
     * 担当テーブルの全取得
     *
     * @return
     */
    @Override
    public List<ResponsibilityRecord> findAllResponsibility() {
        return jdbcTemplate.query("SELECT * FROM responsible ORDER BY id",
                new DataClassRowMapper<>(ResponsibilityRecord.class));
    }

    @Override
    public UserRecord checkIdExist(String id) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("loginId", id);
        var list = jdbcTemplate.query("SELECT * FROM users WHERE login_id = :loginId",
                param, new DataClassRowMapper<>(UserRecord.class));
        return list.isEmpty() ? null : list.get(0);
    }

    /**
     * userの新規追加
     *
     * @param
     * @return
     */
    @Override
    public int insertUser(UserRecord userRecord) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("loginId", userRecord.loginId());
        param.addValue("password", userRecord.password());
        param.addValue("name", userRecord.name());
        param.addValue("responsibleId", userRecord.responsibleId());
        int count = jdbcTemplate.update("INSERT INTO Users" +
                "(login_id, password, name, responsible_id)" +
                " VALUES (:loginId, :password, :name, :responsibleId)", param);
        return count == 1 ? count : null;
    }

    /**
     * IDとPASSからUserを探すメソッド
     *
     * @param idPassRecord
     * @return
     */
    @Override
    public UserRecord findUser(IdPassRecord idPassRecord) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", idPassRecord.loginId());
        param.addValue("pass", idPassRecord.password());
        var list = jdbcTemplate.query("SELECT * FROM users WHERE login_id = :id AND password = :pass",
                param, new DataClassRowMapper<>(UserRecord.class));
        return list.isEmpty() ? null : list.get(0);
    }

    /**
     * 向け地テーブルの取得
     *
     * @param
     * @return
     */
    @Override
    public List<DestinationRecord> selectDestination(int id) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("responsibleId", id);
        return jdbcTemplate.query("SELECT * FROM destination WHERE responsible_id = :responsibleId ORDER BY id", param,
                new DataClassRowMapper<>(DestinationRecord.class));
    }

    /**
     * 向け地テーブルの全取得
     *
     * @param
     * @return
     */
    @Override
    public List<DestinationRecord> findAllDestination() {
        return jdbcTemplate.query("SELECT * FROM destination ORDER BY id",
                new DataClassRowMapper<>(DestinationRecord.class));
    }


    /**
     * ,menuのlistの表示用データ取得
     *
     * @return
     */
    @Override
    public List<ListRecord> findAll(int responsibleId) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("responsibleId", responsibleId);
        return jdbcTemplate.query("SELECT c.id, c.close_date, c.name, d.name AS destination_name, c.lithium, c.reserve_status FROM cargo c INNER JOIN destination d ON c.destination_id = d.id WHERE d.responsible_id = :responsibleId ORDER BY c.close_date DESC", param,
                new DataClassRowMapper<>(ListRecord.class));
    }

    /**
     * ,並び替え用のmenuのlistの表示用データ取得
     *
     * @return
     */
    @Override
    public List<ListRecord> cargoSort(int responsibleId, int reserveNum, int destNum, String keyword) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT c.id, c.close_date, c.name, d.name AS destination_name, c.lithium, c.reserve_status ")
                .append("FROM cargo c INNER JOIN destination d ON c.destination_id = d.id ");
        queryBuilder.append("WHERE d.responsible_id = " + responsibleId);


        if (!keyword.isEmpty()) {
            String[] keywordArray = keyword.split(" ");
            for (int i = 0; i < keywordArray.length; i++) {
                queryBuilder.append(" AND ");

                queryBuilder.append("(c.name LIKE '%" + keywordArray[i] + "%' OR ")
                        .append("d.name LIKE '%" + keywordArray[i] + "%') ");
            }
        }

        if (reserveNum == 1) {
            queryBuilder.append(" AND ");
            queryBuilder.append("c.reserve_status = 1 ");
        } else if (reserveNum == 2) {
            queryBuilder.append(" AND ");
            queryBuilder.append("c.reserve_status = 2 ");
        }

        if (destNum > 0) {
            queryBuilder.append(" AND ");
            queryBuilder.append("c.destination_id = " + destNum);
        }

        String query = queryBuilder.toString();
        return jdbcTemplate.query(query, new DataClassRowMapper<>(ListRecord.class));
    }

    /**
     * airportテーブル情報全取得
     *
     * @return
     */
    @Override
    public List<AirplaneRecord> findAirplaneAll() {
        return jdbcTemplate.query("SELECT * FROM airport ORDER BY id",
                new DataClassRowMapper<>(AirplaneRecord.class));
    }

    /**
     * airportテーブルを更新
     *
     * @param
     * @return
     */
    @Override
    public int airplaneUpdate(AirplaneRecord airplaneRecord) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", airplaneRecord.id());
        param.addValue("mon", airplaneRecord.mon());
        param.addValue("tue", airplaneRecord.tue());
        param.addValue("wed", airplaneRecord.wed());
        param.addValue("thu", airplaneRecord.thu());
        param.addValue("fri", airplaneRecord.fri());
        param.addValue("sat", airplaneRecord.sat());
        param.addValue("sun", airplaneRecord.sun());

        int count = jdbcTemplate.update("UPDATE airport" +
                " SET mon = :mon,  tue = :tue, wed = :wed, thu = :thu, fri = :fri, sat = :sat, sun = :sun" +
                " WHERE id = :id", param);
        return count == 1 ? count : null;
    }

    /**
     * airportテーブルを削除
     *
     * @param
     * @return
     */
    @Override
    public int airplaneDelete(AirplaneRecord airplaneRecord) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", airplaneRecord.id());
        int count = jdbcTemplate.update("DELETE FROM airport WHERE id = :id", param);
        return count == 1 ? count : null;
    }

    /**
     * airportの新規追加
     *
     * @param
     * @return
     */
    @Override
    public int insertAirplane(AirplaneRecord airplaneRecord) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("prefix", airplaneRecord.prefix());
        param.addValue("letterCode", airplaneRecord.letterCode());
        param.addValue("name", airplaneRecord.name());
        param.addValue("country", airplaneRecord.country());
        param.addValue("mon", airplaneRecord.mon());
        param.addValue("tue", airplaneRecord.tue());
        param.addValue("wed", airplaneRecord.wed());
        param.addValue("thu", airplaneRecord.thu());
        param.addValue("fri", airplaneRecord.fri());
        param.addValue("sat", airplaneRecord.sat());
        param.addValue("sun", airplaneRecord.sun());
        int count = jdbcTemplate.update("INSERT INTO airport" +
                "(prefix, letter_code, name, country, mon, tue, wed, thu, fri, sat, sun)" +
                " VALUES (:prefix, :letterCode, :name, :country, :mon, :tue, :wed, :thu, :fri, :sat, :sun)", param);
        return count == 1 ? count : null;
    }

    /**
     * cargoテーブルの新規追加
     *
     * @param cargoRecord
     * @return
     */
    @Override
    public int insertCargo(CargoRecord cargoRecord) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("name", cargoRecord.name());
        param.addValue("destinationId", cargoRecord.destinationId());
        param.addValue("closeDate", cargoRecord.closeDate());
        param.addValue("arrivalDate", cargoRecord.arrivalDate());
        param.addValue("lithium", cargoRecord.lithium());
        param.addValue("height", cargoRecord.height());
        param.addValue("width", cargoRecord.width());
        param.addValue("depth", cargoRecord.depth());
        param.addValue("weight", cargoRecord.weight());
        param.addValue("description", cargoRecord.description());
        param.addValue("reserveStatus", cargoRecord.reserveStatus());

        int count = jdbcTemplate.update("INSERT INTO cargo" +
                        "(name, destination_id, close_date, arrival_date, lithium, height, width, depth, weight, description, reserve_status, created_at, updated_at)" +
                        " VALUES (:name, :destinationId, :closeDate, :arrivalDate, :lithium, :height, :width, :depth, :weight, :description, :reserveStatus, now(), now())",
                param);
        return count == 1 ? count : null;
    }

    /**
     * cargoの詳細用データ取得
     *
     * @param id
     * @return
     */
    @Override
    public CargoRecord findById(int id) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", id);
        var list = jdbcTemplate.query("SELECT * FROM cargo WHERE id = :id",
                param, new DataClassRowMapper<>(CargoRecord.class));
        return list.isEmpty() ? null : list.get(0);
    }

    /**
     * cargoテーブルの更新
     *
     * @param cargoRecord
     * @return
     */
    @Override
    public int updateCargo(CargoRecord cargoRecord) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", cargoRecord.id());
        param.addValue("name", cargoRecord.name());
        param.addValue("destinationId", cargoRecord.destinationId());
        param.addValue("closeDate", cargoRecord.closeDate());
        param.addValue("arrivalDate", cargoRecord.arrivalDate());
        param.addValue("lithium", cargoRecord.lithium());
        param.addValue("height", cargoRecord.height());
        param.addValue("width", cargoRecord.width());
        param.addValue("depth", cargoRecord.depth());
        param.addValue("weight", cargoRecord.weight());
        param.addValue("description", cargoRecord.description());
        param.addValue("reserveStatus", cargoRecord.reserveStatus());

        int count = jdbcTemplate.update("UPDATE cargo SET " +
                        "name = :name, " +
                        "destination_id = :destinationId, " +
                        "close_date = :closeDate, " +
                        "arrival_date = :arrivalDate, " +
                        "lithium = :lithium, " +
                        "height = :height, " +
                        "width = :width, " +
                        "depth = :depth, " +
                        "weight = :weight, " +
                        "description = :description, " +
                        "reserve_status = :reserveStatus, " +
                        "updated_at = now() " +
                        "WHERE id = :id",
                param);
        return count == 1 ? count : null;
    }

    /**
     * cargoテーブルを削除
     *
     * @param
     * @return
     */
    @Override
    public int deleteCargo(CargoRecord cargoRecord) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", cargoRecord.id());
        int count = jdbcTemplate.update("DELETE FROM cargo WHERE id = :id", param);
        return count == 1 ? count : null;
    }
}
