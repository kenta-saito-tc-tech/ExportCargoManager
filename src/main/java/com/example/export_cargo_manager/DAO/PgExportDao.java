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
     * 向け地テーブルの全取得
     *
     * @param
     * @return
     */
    @Override
    public List<DestinationRecord> findAllDestination(int id) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("responsibleId", id);
        return jdbcTemplate.query("SELECT * FROM destination WHERE responsible_id = :responsibleId ORDER BY id", param,
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
        queryBuilder.append("WHERE d.responsible_id = "+responsibleId);


        if (!keyword.isEmpty()) {
            String[] keywordArray = keyword.split(" ");
            for (int i = 0; i < keywordArray.length; i++) {
                queryBuilder.append(" AND ");

                queryBuilder.append("(c.name LIKE '%" + keywordArray[i] + "%' OR ")
                        .append("d.name LIKE '%" + keywordArray[i] + "%') ");
            }
        }

        if(reserveNum == 1){
            queryBuilder.append(" AND ");
            queryBuilder.append("c.reserve_status = 1 ");
        } else if (reserveNum == 2) {
            queryBuilder.append(" AND ");
            queryBuilder.append("c.reserve_status = 2 ");
        }

        if(destNum > 0){
            queryBuilder.append(" AND ");
            queryBuilder.append("c.destination_id = " +destNum);
        }

        String query = queryBuilder.toString();
        return jdbcTemplate.query(query, new DataClassRowMapper<>(ListRecord.class));
    }
}
