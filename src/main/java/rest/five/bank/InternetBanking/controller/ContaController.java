package rest.five.bank.InternetBanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rest.five.bank.InternetBanking.Business.ContaBusiness;
import rest.five.bank.InternetBanking.controller.dto.DepositoDTO;
import rest.five.bank.InternetBanking.model.Conta;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/conta")
@CrossOrigin
public class ContaController {

    @Autowired
    ContaBusiness contaBusiness;

    @GetMapping("/contaAdd")
    public Object addConta() {
        return contaBusiness.addConta();
    }

    @GetMapping("/buscarConta")
    public Conta buscarConta(@RequestParam Long id) {
        return contaBusiness.buscarConta(id);
    }

    @GetMapping("/listaContas")
    public List<Conta> buscarContas() {
        return contaBusiness.buscarContas();
    }

    @PutMapping("/depositar")
    @Transactional
    public void depositar(@RequestBody @Valid DepositoDTO dpDTO) {
        contaBusiness.depositar(dpDTO);
    }
}
