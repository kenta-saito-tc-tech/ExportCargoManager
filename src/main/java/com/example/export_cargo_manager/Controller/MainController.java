package com.example.export_cargo_manager.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    /**
     * 初期画面
     * @return
     */
    @GetMapping("/index")
    public String firstView() {
        return "/index";
    }
    /**
     * アカウント登録画面
     * @return
     */
    @GetMapping("/new_account")
    public String newAccountView() {
        return "/new_account";
    }
    /**
     * ログイン画面
     * @return
     */
    @GetMapping("/login")
    public String loginView() {
        return "/login";
    }

    /**
     * メインページ画面
     */
    @GetMapping("/main-page")
    public String mainView(){return "//main-page";}


}
