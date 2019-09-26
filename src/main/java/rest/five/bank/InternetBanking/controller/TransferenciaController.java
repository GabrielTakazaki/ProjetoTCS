package rest.five.bank.InternetBanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rest.five.bank.InternetBanking.Business.TransferenciaBusiness;
import rest.five.bank.InternetBanking.controller.dto.TransferenciaDTO;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transferencia")
@CrossOrigin

public class TransferenciaController {
    @Autowired
    TransferenciaBusiness transferenciaBusiness;

    @PostMapping("/addTransf")
    public TransferenciaDTO addTrans(@RequestBody @Valid TransferenciaDTO transferenciaDTO) {
        return transferenciaBusiness.addTrans(transferenciaDTO);
    }

    @GetMapping("/listAllTransf")
    public List<TransferenciaDTO> listaTudoComId(@RequestParam Long id) {
        return transferenciaBusiness.listaTudoComId(id);
    }
}
