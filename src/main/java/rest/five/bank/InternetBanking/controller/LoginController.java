package rest.five.bank.InternetBanking.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/")
    public void addLogin(){

    }

    @RequestMapping("/verificaLogin")
    public boolean verificaLogin(){
        return true;
    }
}
