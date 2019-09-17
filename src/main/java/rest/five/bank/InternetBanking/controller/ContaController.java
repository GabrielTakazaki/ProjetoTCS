package rest.five.bank.InternetBanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rest.five.bank.InternetBanking.entities.ClienteInterface;
import rest.five.bank.InternetBanking.entities.ContaInterface;
import rest.five.bank.InternetBanking.model.Cliente;
import rest.five.bank.InternetBanking.model.Conta;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/conta")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class ContaController {

    @Autowired
    ContaInterface contaInterface;
    @Autowired
    ClienteInterface clienteInterface;

    @GetMapping("/contaAdd")
    public Object addConta(@RequestParam Long id) {

        Optional<Cliente> optionalCliente = clienteInterface.findById(id);
        Cliente cliente = optionalCliente.get();
        if (contaExiste(cliente))
            return contaInterface.findByFkIdCliente(cliente);
        else {
            return contaInterface.save(criaConta(cliente));
        }
    }

    public boolean contaExiste(Cliente cliente) {
        return contaInterface.existsByFkIdCliente(cliente);
    }

    private List<Conta> buscarContas() {
        return contaInterface.findAll();
    }

    @GetMapping("/buscarConta")
    public Conta buscarConta(@RequestParam Long id) {
        Optional<Conta> optConta = contaInterface.findById(id);
        return optConta.get();
    }


    public Conta criaConta(Cliente cliente) {
        Conta conta = new Conta();
        conta.setSaldoConta(0);
        conta.gerarNumConta(buscarContas());
        conta.setFkIdCliente(cliente);
        return conta;
    }
}
