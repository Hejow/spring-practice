package com.hejow.deepdivesecurity.global;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
    @GetMapping("/")
    public String index() {
        return "/index";
    }

    @GetMapping("/mypage")
    public String mypage() {
        return "/mypage";
    }

    @GetMapping("/admin/1")
    public String admin() {
        return "/admin";
    }

    @GetMapping("/admin/2")
    public String admin2() {
        return "/admin2";
    }
}
