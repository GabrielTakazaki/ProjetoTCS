package rest.five.bank.InternetBanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rest.five.bank.InternetBanking.entities.LoginInterface;
import rest.five.bank.InternetBanking.model.Login;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class LoginController {

    @Autowired
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
    @PostMapping("/verificaLogin")
    public boolean verificaLogin(@RequestBody Login login) {
        List<Login> listUsers = loginInterface.findAll();
        for (Login user : listUsers){
            if(login.getUsername() == user.getUsername()){
                if (login.getPassword() == user.getPassword())
                    return true;
                else return false;
            }
        }
        return false;
    }

}
