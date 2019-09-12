package rest.five.bank.InternetBanking.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rest.five.bank.InternetBanking.entities.LoginInterface;

@RestController
@RequestMapping("/login")
public class LoginController {

    private LoginInterface loginInterface;
    @RequestMapping("/")
    public void addLogin(){

    }

    @RequestMapping("/verificaLogin")
    public boolean verificaLogin(){
        return true;
    }
}
