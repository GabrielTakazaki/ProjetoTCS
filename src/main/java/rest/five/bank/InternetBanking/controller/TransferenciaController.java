package rest.five.bank.InternetBanking.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rest.five.bank.InternetBanking.entities.ContaCorrentInterface;
import rest.five.bank.InternetBanking.entities.TransferenciaInterface;
import rest.five.bank.InternetBanking.model.Conta;
import rest.five.bank.InternetBanking.model.Transferencia;

import java.util.Optional;

@RestController
@RequestMapping("/transferencia")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class TransferenciaController {

    TransferenciaInterface transferenciaInterface;
    ContaCorrentInterface contaCorrentInterface;

    @PostMapping("/addTransf")
    public Transferencia addTrans(Transferencia transf, Long iddebito, Long idcredito){
        Optional<Conta> optCDebito = contaCorrentInterface.findById(iddebito);
        Optional<Conta> optCCredito = contaCorrentInterface.findById(idcredito);
        transf.setContaDebito(optCDebito.get());
        transf.setContaCredito(optCCredito.get());
        return transferenciaInterface.save(transf);
    }
}
