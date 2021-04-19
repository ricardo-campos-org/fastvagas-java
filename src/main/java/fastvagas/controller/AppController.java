package fastvagas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/app")
    public String appIndex() {
        return "app/index";
    }

    @GetMapping("/termos-de-busca")
    public String termosDeBusca() {
        return "app/termos";
    }

    @GetMapping("/minha-conta")
    public String minhaConta() {
        return "app/minha_conta";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "error/acess-denied";
    }
}
