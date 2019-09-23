package rest.five.bank.InternetBanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rest.five.bank.InternetBanking.dto.DepositoDTO;
import rest.five.bank.InternetBanking.entities.ClienteInterface;
import rest.five.bank.InternetBanking.entities.ContaInterface;
import rest.five.bank.InternetBanking.entities.CreditoEspecialInterface;
import rest.five.bank.InternetBanking.model.Cliente;
import rest.five.bank.InternetBanking.model.Conta;
import rest.five.bank.InternetBanking.model.CreditoEspecial;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/conta")
@CrossOrigin
public class ContaController {

    @Autowired
    ContaInterface contaInterface;
    @Autowired
    ClienteInterface clienteInterface;
    @Autowired
    CreditoEspecialInterface cdInterface;

    Conta conta;

    @GetMapping("/contaAdd")
    public Object addConta(@RequestParam Long id) {

        Optional<Cliente> optionalCliente = clienteInterface.findById(id);
        Cliente cliente = optionalCliente.get();
        if (contaExiste(cliente)) {
            this.conta = contaInterface.findByFkIdCliente(cliente);
            return this.conta;
        }
        else {
            this.conta = contaInterface.save(criaConta(cliente));
            return this.conta;
        }
    }

    @GetMapping("/buscarConta")
    public Conta buscarConta(@RequestParam Long id) {
        Optional<Conta> optConta = contaInterface.findById(id);
        return optConta.get();
    }


    public boolean contaExiste(Cliente cliente) {
        return contaInterface.existsByFkIdCliente(cliente);
    }

    @GetMapping("/listaContas")
    public List<Conta> buscarContas() {
        return contaInterface.findAll();
    }

    public Conta criaConta(Cliente cliente) {
        Conta conta = new Conta();

        conta.setSaldoConta(0);
        conta.setFkIdCliente(cliente);
        return conta;
    }

    @PutMapping("/depositar")
    @Transactional
    public Object depositar(@RequestBody DepositoDTO dpDTO) {
        Optional<Conta> optC = contaInterface.findById(dpDTO.getIdConta());
        optC.get().setSaldoConta(optC.get().getSaldoConta() + dpDTO.getValorDeposito());
        if (cdInterface.existsByFkIdConta(optC.get())) {
            CreditoEspecial cd = cdInterface.findByFkIdConta(optC.get());
            cd.setValorSaldo(cd.getValorSaldo() - dpDTO.getValorDeposito());
            if (cd.getValorSaldo() <= 0) {
                cd.setFkIdConta(null);
                cdInterface.save(cd);
                cdInterface.delete(cd);
            }
        }
        return "Deposito realizado com sucesso!";
    }
}
