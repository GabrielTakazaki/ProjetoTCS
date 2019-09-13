package rest.five.bank.InternetBanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rest.five.bank.InternetBanking.entities.ClienteInterface;
import rest.five.bank.InternetBanking.entities.ContaInterface;
import rest.five.bank.InternetBanking.model.Cliente;
import rest.five.bank.InternetBanking.model.Conta;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/conta")
//@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class ContaController {

    @Autowired
    ContaInterface contaInterface;
    @Autowired
    ClienteInterface clienteInterface;

    @GetMapping("/contaAdd")
    public Object addConta(@RequestParam Long id) throws Exception {
        try {
            Optional<Cliente> optionalCliente = clienteInterface.findById(id);
            Cliente cliente = optionalCliente.get();
            if (contaExiste(id))
                return contaInterface.findByFkIdCliente(cliente.getIdCliente());
            else {
                return contaInterface.save(criaConta(cliente));
            }
        } catch (Exception e) {
            return "Cliente ja possui conta";
        }
    }

    public boolean contaExiste(Long id) {
        return contaInterface.findByFkIdCliente(id) == null;
    }

    public List<Conta> buscarContas() {
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
