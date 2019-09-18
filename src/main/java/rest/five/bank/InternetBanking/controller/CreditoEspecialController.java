package rest.five.bank.InternetBanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rest.five.bank.InternetBanking.entities.ContaInterface;
import rest.five.bank.InternetBanking.entities.CreditoEspecialInterface;
import rest.five.bank.InternetBanking.model.Conta;
import rest.five.bank.InternetBanking.model.CreditoEspecial;

import java.util.Optional;

@RestController
@RequestMapping("/credito")
//@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class CreditoEspecialController {

    @Autowired
    CreditoEspecialInterface cdInterface;
    @Autowired
    ContaInterface contaInterface;

    @PostMapping("/creditoAdd")
    public Object save(@RequestBody CreditoEspecial cdEspecial) {
        Optional<Conta> optC = contaInterface.findById(cdEspecial.getFkIdConta().getNumConta());
        Conta conta = optC.get();
        cdEspecial.setFkIdConta(conta);
        if (cdInterface.existsByFkIdConta(conta)) {
            return "Seu crédito especial está ativo.";
        }
        if (conta.getSaldoConta() == 0) {
            if (cdEspecial.getValorSaldo() <= 600) {

                return cdInterface.save(cdEspecial);
            } else
                return "O crédito que você pediu é superior ao permitido";
        } else
            return "Seu saldo não está zerado";
    }

    @GetMapping("/findCredito")
    public CreditoEspecial findCredito(@RequestParam Long id) {
        Optional<CreditoEspecial> optC = cdInterface.findById(id);
        return optC.get();
    }

    @PutMapping("/adicionaCredito")
    public Object updateCredito(@RequestBody CreditoEspecial maisCredito) {
        Optional<Conta> optC = contaInterface.findById(maisCredito.getFkIdConta().getNumConta());
        CreditoEspecial optCred = cdInterface.findByFkIdConta(optC.get());
        if ((optCred.getValorSaldo() + maisCredito.getValorSaldo()) < 600) {
            optCred.setValorSaldo(optCred.getValorSaldo() + maisCredito.getValorSaldo());
            return cdInterface.save(optCred);
        } else {
            return "Atigido o limite do crédito especial";
        }
    }
}