package rest.five.bank.InternetBanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rest.five.bank.InternetBanking.entities.LoginInterface;
import rest.five.bank.InternetBanking.model.Cliente;

@RestController
@RequestMapping("/login")
//@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class LoginController {

    @Autowired
    private LoginInterface loginInterface;
    //==================================================================
    // Adicionar nova conta
    //==================================================================
    @PostMapping("/addLogin")
    public boolean addLogin(Cliente login) {
        if (login.validaSenha()) {
            loginInterface.save(login);
            return true;
        } else return false;
    }

    //==================================================================
    // Verifica conta Existente
    //==================================================================
/* -------------------ARRUMAR-----------------------------------
    @PostMapping("/verificaLogin")
    public boolean verificaLogin(Cliente login) {
        List<Cliente> listUsers = loginInterface.findAll();
        for (Cliente user : listUsers) {
            if (login.getUsername().equals(user.getUsername())) {
                if (login.getPassword().equals(user.getPassword()))
                    return true;
                else return false;
            }
        }
        return false;
    }
*/
}
