package rest.five.bank.InternetBanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rest.five.bank.InternetBanking.dto.TransferenciaDTO;
import rest.five.bank.InternetBanking.entities.ContaInterface;
import rest.five.bank.InternetBanking.entities.TransferenciaInterface;
import rest.five.bank.InternetBanking.model.Conta;
import rest.five.bank.InternetBanking.model.Transferencia;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transferencia")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")

public class TransferenciaController {
    @Autowired
    TransferenciaInterface transferenciaInterface;
    @Autowired
    ContaInterface contaInterface;

    @PostMapping("/addTransf")
    public Transferencia addTrans(@RequestBody TransferenciaDTO transferenciaDTO) {
        Transferencia transf = new Transferencia();

        Optional<Conta> optCDebito = contaInterface.findById(transferenciaDTO.getIdDebitoDTO());
        Optional<Conta> optCCredito = contaInterface.findById(transferenciaDTO.getIdCreditoDTO());

        transf.setContaDebito(optCDebito.get());
        transf.setContaCredito(optCCredito.get());
        transf.setValTransferencia(transferenciaDTO.getValorTransferenciaDTO());
        transf.setDtTransferencia(LocalDateTime.now());

        calculaConta(optCCredito.get(), optCDebito.get(), transferenciaDTO.getValorTransferenciaDTO());
        return transferenciaInterface.save(transf);
    }

    @GetMapping("/extratoTransf")
    public List<Transferencia> listTransf(@RequestParam Long idConta) {
        System.out.println(idConta);
        List<Transferencia> listaTransferencia = new ArrayList<>();
        for (Transferencia transf : transferenciaInterface.findAll()) {
            if (transf.getContaDebito().getNumConta().equals(idConta) || transf.getContaCredito().getNumConta().equals(idConta)) {
                listaTransferencia.add(transf);
            }
        }
        return listaTransferencia;
    }

    public void calculaConta(Conta contaCredito, Conta contaDebito, float valorTransferencia) {
        contaCredito.setSaldoConta(contaCredito.getSaldoConta() + valorTransferencia);
        contaDebito.setSaldoConta(contaDebito.getSaldoConta() - valorTransferencia);
        contaInterface.save(contaCredito);
        contaInterface.save(contaDebito);
    }
}
