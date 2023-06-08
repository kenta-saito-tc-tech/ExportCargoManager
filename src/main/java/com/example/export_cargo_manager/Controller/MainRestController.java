package com.example.export_cargo_manager.Controller;

import com.example.export_cargo_manager.Entity.*;
import com.example.export_cargo_manager.Service.ExportService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@Validated
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
     *
     * @param
     * @return
     */
    @PostMapping("/insertUser")
    public ResponseEntity<String> insertProduct(@Valid @RequestBody UserRecord userRecord) {
        try {
            UserRecord user = exportService.checkIdExist(userRecord.loginId());
            if (user == null) {
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
            } else {
                return new ResponseEntity<>("product_id exists", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("POST request failed", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * ログイン時のID、PASSチェック
     *
     * @param idPassRecord
     * @return
     */
    @PostMapping("/log-check")
    public ResponseEntity<String> insertView(@Valid @RequestBody IdPassRecord idPassRecord) {
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
     * 向け地テーブルの取得
     *
     * @param
     * @return
     */
    @GetMapping("/destination")
    public List<DestinationRecord> selectDestination(@RequestParam(name = "searchId") int responsibleId) {
        try {
            var list = exportService.selectDestination(responsibleId);
            return list; //ステータスコード200番
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
    @GetMapping("/destinations")
    public List<DestinationRecord> findAllDestination() {
        try {
            var list = exportService.findAllDestination();
            return list; //ステータスコード200番
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST); //ステータスコード400番
        }
    }

    /**
     * ,menuのlistの表示用データ取得
     *
     * @return
     */
    @GetMapping("/cargos")
    public List<ListRecord> cargoList(@RequestParam(name = "searchId") int responsibleId) {
        try {
            var list = exportService.findAll(responsibleId);
            return list; //ステータスコード200番
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST); //ステータスコード400番
        }
    }

    /**
     * productの並び順変更
     *
     * @param
     * @return
     */
    @GetMapping("/cargoSort")
    public List<ListRecord> cargoSort(@RequestParam(name = "searchId") int responsibleId, @RequestParam(name = "changeMenu1") int reserveNum, @RequestParam(name = "changeMenu2") int destNum, @RequestParam(name = "keyword") String keyword) {
        try {
            System.out.println(responsibleId);
            var pr = exportService.cargoSort(responsibleId, reserveNum, destNum, keyword);
            System.out.println(pr);
            return pr; //ステータスコード200番
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST); //ステータスコード400番
        }
    }

    /**
     * airplaneテーブル情報全取得
     *
     * @param
     * @return
     */
    @GetMapping("/airplanes")
    public List<AirplaneRecord> selectCategories() {
        try {
            var list = exportService.findAirplaneAll();
            return list; //ステータスコード200番
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST); //ステータスコード400番
        }
    }

    /**
     * airportテーブルの更新
     *
     * @param
     * @return
     */
    @PutMapping("/airplaneUpdate")
    public ResponseEntity<String> categoryUpdate(@Valid @RequestBody AirplaneRecord airplaneRecord) {
        try {
            int count = exportService.airplaneUpdate(airplaneRecord);
            if (count == 1) {
                return new ResponseEntity<>("PUT request processed", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("PUT request failed", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST); //ステータスコード400番
        }
    }

    /**
     * airportテーブルの削除
     *
     * @param
     * @return
     */
    @DeleteMapping("/airplaneDelete")
    public ResponseEntity<String> categoryDelete(@Valid @RequestBody AirplaneRecord airplaneRecord) {
        try {
            int count = exportService.airplaneDelete(airplaneRecord);
            if (count == 1) {
                return new ResponseEntity<>("DELETE request processed", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("DELETE request failed", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST); //ステータスコード400番
        }
    }

    /**
     * airportテーブルの追加
     * @param
     * @return
     */
    @PostMapping("/insertAirplane")
    public ResponseEntity<String> insertAirplane(@Valid @RequestBody AirplaneRecord airplaneRecord) {

        try {
            int count = exportService.insertAirplane(airplaneRecord);
            if (count == 1) {
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
     * cargoテーブルの追加
     * @param
     * @return
     */
    @PostMapping("/insertCargo")
    public ResponseEntity<String> insertCargo(@Valid @RequestBody CargoRecord cargoRecord) {

        try {
            int count = exportService.insertCargo(cargoRecord);
            if (count == 1) {
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
     * cargoの詳細ページ用データ取得用
     * @param
     * @return
     */
    @GetMapping("/cargo")
    public CargoRecord cargoIdFind(@RequestParam(name = "searchId") int id){
        try {
            var pr = exportService.findById(id);
            return pr; //ステータスコード200番
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST); //ステータスコード400番
        }
    }

    /**
     * cargoテーブルの更新
     * @param
     * @return
     */
    @PutMapping("/updateCargo")
    public ResponseEntity<String> updateCargo(@Valid @RequestBody CargoRecord cargoRecord) {

        try {
            int count = exportService.updateCargo(cargoRecord);
            if (count == 1) {
                return new ResponseEntity<>("PUT request processed", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("PUT request failed", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST); //ステータスコード400番
        }
    }

    /**
     * cargoテーブルの削除
     *
     * @param
     * @return
     */
    @DeleteMapping("/deleteCargo")
    public ResponseEntity<String> deleteCargo(@Valid @RequestBody CargoRecord cargoRecord) {
        try {
            System.out.println("----------------------------------------------");
            System.out.println(cargoRecord);
            System.out.println("----------------------------------------------");
            int count = exportService.deleteCargo(cargoRecord);
            System.out.println(count);
            System.out.println("----------------------------------------------");
            if (count == 1) {
                return new ResponseEntity<>("DELETE request processed", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("DELETE request failed", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST); //ステータスコード400番
        }
    }
}
