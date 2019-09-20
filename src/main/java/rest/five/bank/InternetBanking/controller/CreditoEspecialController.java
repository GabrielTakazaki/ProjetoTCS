package rest.five.bank.InternetBanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rest.five.bank.InternetBanking.dto.CreditoDTO;
import rest.five.bank.InternetBanking.entities.ContaInterface;
import rest.five.bank.InternetBanking.entities.CreditoEspecialInterface;
import rest.five.bank.InternetBanking.model.Conta;
import rest.five.bank.InternetBanking.model.CreditoEspecial;

import javax.transaction.Transactional;
import java.util.Optional;

@RestController
@RequestMapping("/credito")
@CrossOrigin
public class CreditoEspecialController {

    @Autowired
    CreditoEspecialInterface cdInterface;
    @Autowired
    ContaInterface contaInterface;

    @PostMapping("/creditoAdd")
    @Transactional
    public String save(@RequestBody CreditoDTO cdEspecial) {
        Optional<Conta> optC = contaInterface.findById(cdEspecial.getIdCliente());
        Conta conta = optC.get();
        CreditoEspecial cdEspecialNew = new CreditoEspecial();
        cdEspecialNew.setFkIdConta(conta);
        cdEspecialNew.setValorSaldo(cdEspecial.getValorSaldo());
        if (cdInterface.existsByFkIdConta(conta)) {
            return "Seu crédito especial está ativo.";
        }
        if (conta.getSaldoConta() == 0) {
            if (cdEspecial.getValorSaldo() <= 600) {
                conta.setSaldoConta(conta.getSaldoConta() - cdEspecial.getValorSaldo());
                cdInterface.save(cdEspecialNew);
                return "Pedito aceito com sucesso!";
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