package com.example.export_cargo_manager.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {
    @Autowired
    private HttpSession session;

    /**
     * 初期画面
     *
     * @return
     */
    @GetMapping("/index")
    public String firstView() {
        if (session != null) {
            session.invalidate(); // セッションを無効化して削除する
        }
        return "/index";
    }

    /**
     * アカウント登録画面
     *
     * @return
     */
    @GetMapping("/new_account")
    public String newAccountView() {
        if (session != null) {
            session.invalidate(); // セッションを無効化して削除する
        }
        return "/new_account";
    }

    /**
     * ログイン画面
     *
     * @return
     */
    @GetMapping("/login")
    public String loginView() {
        if (session != null) {
            session.invalidate(); // セッションを無効化して削除する
        }
        return "/login";
    }

    /**
     * メインページ画面
     */
    @GetMapping("/main-page")
    public String mainView() {
        if (session.getAttribute("user") == null) { //sessionがない場合
            return "redirect:/index";
        }

        return "/main-page";
    }

    /**
     * 航空会社管理画面
     */
    @GetMapping("/airplane")
    public String airplaneView() {
        if (session.getAttribute("user") == null) { //sessionがない場合
            return "redirect:/index";
        }

        return "/airplane";
    }

    /**
     * 貨物追加画面
     */
    @GetMapping("/new_cargo")
    public String newCargoView() {
        if (session.getAttribute("user") == null) { //sessionがない場合
            return "redirect:/index";
        }

        return "/new_cargo";
    }

    /**
     * cargo情報画面
     *
     * @return
     */
    @GetMapping("/detail_cargo/{id}")
    public String detailView(@PathVariable("id") int id, Model model) {
        if (session.getAttribute("user") == null) { //sessionがない場合
            return "redirect:/index";
        }
        model.addAttribute("id", id);
        return "/detail_cargo";
    }

}


