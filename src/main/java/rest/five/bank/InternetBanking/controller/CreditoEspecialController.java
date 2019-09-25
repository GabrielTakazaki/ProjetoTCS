package rest.five.bank.InternetBanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rest.five.bank.InternetBanking.Business.CreditoEspecialBusiness;
import rest.five.bank.InternetBanking.controller.dto.CreditoDTO;
import rest.five.bank.InternetBanking.model.CreditoEspecial;

import javax.validation.Valid;

@RestController
@RequestMapping("/credito")
@CrossOrigin
public class CreditoEspecialController {

    @Autowired
    CreditoEspecialBusiness creditoEspecialBusiness;

    @PostMapping("/creditoAdd")
    public void save(@RequestBody @Valid CreditoDTO cdEspecial) {
        creditoEspecialBusiness.save(cdEspecial);
    }

    @GetMapping("/findCredito")
    public CreditoEspecial findCredito(@RequestParam Long id) {
        return creditoEspecialBusiness.findCredito(id);
    }

    @PutMapping("/adicionaCredito")
    public Object updateCredito(@RequestBody CreditoEspecial maisCredito) {
        return creditoEspecialBusiness.updateCredito(maisCredito);
    }
}