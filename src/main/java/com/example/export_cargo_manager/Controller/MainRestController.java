package com.example.export_cargo_manager.Controller;

import com.example.export_cargo_manager.Entity.*;
import com.example.export_cargo_manager.Service.ExportService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class MainRestController {
    @Autowired
    private ExportService exportService;

    @Autowired
    private HttpSession session;

    /**
     * 担当テーブルの全取得
     *
     * @param
     * @return
     */
    @GetMapping("/responsibility")
    public List<ResponsibilityRecord> selectResponsibility() {
        try {
            var list = exportService.findAllResponsibility();
            return list; //ステータスコード200番
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST); //ステータスコード400番
        }
    }

    /**
     * userテーブルの追加
     * @param
     * @return
     */
    @PostMapping("/insertUser")
    public ResponseEntity<String> insertProduct(@RequestBody UserRecord userRecord) {
        try {
            UserRecord user = exportService.checkIdExist(userRecord.loginId());
            if (user == null){
                try {
                    int count = exportService.insertUser(userRecord);
                    if (count == 1) {
                        return new ResponseEntity<>("POST request processed", HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>("POST request failed", HttpStatus.BAD_REQUEST);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST); //ステータスコード400番
                }
            }else {
                return new ResponseEntity<>("product_id exists", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("POST request failed", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * ログイン時のID、PASSチェック
     * @param idPassRecord
     * @return
     */
    @PostMapping("/log-check")
    public ResponseEntity<String> insertView(@RequestBody IdPassRecord idPassRecord) {
        try {
            UserRecord user = exportService.findUser(idPassRecord);
            if (user != null) {
                session.setAttribute("user", user);
                return new ResponseEntity<>("POST request processed", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("POST request failed", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST); //ステータスコード400番
        }
    }

    /**
     * 向け地テーブルの全取得
     *
     * @param
     * @return
     */
    @GetMapping("/destination")
    public List<DestinationRecord> selectDestination(@RequestParam(name = "searchId") int responsibleId) {
        try {
            var list = exportService.findAllDestination(responsibleId);
            return list; //ステータスコード200番
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST); //ステータスコード400番
        }
    }

    /**
     * ,menuのlistの表示用データ取得
     * @return
     */
    @GetMapping("/cargos")
    public List<ListRecord> cargoList(@RequestParam(name = "searchId") int responsibleId){
        try {
            var list = exportService.findAll(responsibleId);
            return list; //ステータスコード200番
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST); //ステータスコード400番
        }
    }
}
