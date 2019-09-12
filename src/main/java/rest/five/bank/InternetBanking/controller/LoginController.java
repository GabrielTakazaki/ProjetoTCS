package rest.five.bank.InternetBanking.controller;

import org.springframework.web.bind.annotation.*;
import rest.five.bank.InternetBanking.entities.LoginInterface;
import rest.five.bank.InternetBanking.model.Login;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class LoginController {

    private LoginInterface loginInterface;

    //==================================================================
    // Adicionar nova conta
    //==================================================================
    @PostMapping("/addLogin")
    public boolean addLogin(Login login) {
        if (login.validaSenha()) {
            loginInterface.save(login);
            return true;
        } else return false;
    }

    //==================================================================
    // Verifica conta Existente
    //==================================================================
    @GetMapping("/verificaLogin")
    public boolean verificaLogin(HttpSession sessao, @RequestBody Login login) {
        return false;
    }

}
