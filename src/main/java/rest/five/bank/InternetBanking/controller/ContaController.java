package rest.five.bank.InternetBanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rest.five.bank.InternetBanking.Business.ContaBusiness;
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
    public Object depositar(@RequestBody DepositoDTO dpDTO) {
        return contaBusiness.depositar(dpDTO);
    }
}
