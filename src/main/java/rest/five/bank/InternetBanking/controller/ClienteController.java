package rest.five.bank.InternetBanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rest.five.bank.InternetBanking.entities.ClienteInterface;
import rest.five.bank.InternetBanking.model.Cliente;

import java.util.List;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class ClienteController {

    @Autowired
    private ClienteInterface clienteInterface;
    //==================================================================
    // Adicionar nova conta
    //==================================================================
    @PostMapping("/addLogin")
    public boolean addLogin(Cliente login) {
        if (login.validaSenha()) {
            clienteInterface.save(login);
            return true;
        } else return false;
    }

    //==================================================================
    // Verifica conta Existente
    //==================================================================

    @PostMapping("/verificaLogin")
    public boolean verificaLogin(Cliente login) {
        List<Cliente> listUsers = clienteInterface.findAll();
        for (Cliente user : listUsers) {
            if (login.getCpfCliente().equals(user.getCpfCliente())) {
                if (login.getPassword().equals(user.getPassword()))
                    return true;
                else return false;
            }
        }
        return false;
    }
}
