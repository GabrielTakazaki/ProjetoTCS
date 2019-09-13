package rest.five.bank.InternetBanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rest.five.bank.InternetBanking.entities.ClienteInterface;
import rest.five.bank.InternetBanking.model.Cliente;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class ClienteController {

    @Autowired
    private ClienteInterface clienteInterface;
    //==================================================================
    // Adicionar nova conta
    //==================================================================
    @PostMapping("/clienteAdd")
    public String addLogin(Cliente cliente) throws Exception {
        try {
            if (cliente.validaSenha()) {
                clienteInterface.save(cliente);
                return "Cadastrado";
            } else return "Senha Pequena";
        } catch (Exception e) {
            return "Cpf ja existe";
        }
    }

    //==================================================================
    // Verifica conta Existente
    //==================================================================

    @PostMapping("/verificaCliente")
    public boolean verificaLogin(Cliente cliente) {
        List<Cliente> listUsers = buscarClientes();
        for (Cliente user : listUsers) {
            if (cliente.getCpfCliente().equals(user.getCpfCliente())) {
                return cliente.getPassword().equals(user.getPassword());
            }
        }
        return false;
    }

    @GetMapping("/buscarTodos")
    public List<Cliente> buscarClientes() {
        return clienteInterface.findAll();
    }

    @GetMapping("/buscarCliente")
    public Cliente buscarCliente(@RequestParam Long id) {
        Optional<Cliente> optCliente = clienteInterface.findById(id);
        return optCliente.get();
    }


}
