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
@CrossOrigin

public class TransferenciaController {
    @Autowired
    TransferenciaInterface transferenciaInterface;
    @Autowired
    ContaInterface contaInterface;

    @PostMapping("/addTransf")
    public TransferenciaDTO addTrans(@RequestBody TransferenciaDTO transferenciaDTO) {
        System.out.println(transferenciaDTO.getIdDebitoDTO());
        System.out.println(transferenciaDTO.getIdCreditoDTO());
        System.out.println(transferenciaDTO.getValorTransferenciaDTO());
        Transferencia transf = new Transferencia();

        Optional<Conta> optCDebito = contaInterface.findById(transferenciaDTO.getIdDebitoDTO());
        Optional<Conta> optCCredito = contaInterface.findById(transferenciaDTO.getIdCreditoDTO());

        transf.setContaDebito(optCDebito.get());
        transf.setContaCredito(optCCredito.get());
        transf.setValTransferencia(transferenciaDTO.getValorTransferenciaDTO());
        transf.setDtTransferencia(LocalDateTime.now());

        calculaConta(optCCredito.get(), optCDebito.get(), transferenciaDTO.getValorTransferenciaDTO());
        transferenciaInterface.save(transf);
        return new TransferenciaDTO(transf);
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

    @GetMapping("/listAllTransf")
    public List<TransferenciaDTO> listaTudoComId(@RequestParam Long id) {
        List<TransferenciaDTO> transferenciaDTOList = new ArrayList<>();
        List<Transferencia> transList = transferenciaInterface.findAll();

        transList.forEach((item) -> {
            if (id.equals(item.getContaCredito().getNumConta())) {
                transferenciaDTOList.add(new TransferenciaDTO(item));
            } else if (id.equals(item.getContaDebito().getNumConta())) {
                transferenciaDTOList.add(new TransferenciaDTO(item));
            }
        });
        System.out.println(transferenciaDTOList.size());
        return transferenciaDTOList;
    }

    public void calculaConta(Conta contaCredito, Conta contaDebito, float valorTransferencia) {
        contaCredito.setSaldoConta(contaCredito.getSaldoConta() + valorTransferencia);
        contaDebito.setSaldoConta(contaDebito.getSaldoConta() - valorTransferencia);
        contaInterface.save(contaCredito);
        contaInterface.save(contaDebito);
    }
}
