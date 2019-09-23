package rest.five.bank.InternetBanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rest.five.bank.InternetBanking.Business.InvestimentoBusiness;
import rest.five.bank.InternetBanking.controller.dto.InvestimentoDTO;
import rest.five.bank.InternetBanking.entities.ContaInterface;
import rest.five.bank.InternetBanking.entities.InvestimentoInterface;
import rest.five.bank.InternetBanking.model.Investimento;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/investimento")
@CrossOrigin
public class InvestimentoController {
    @Autowired
    private InvestimentoInterface investimentoInterface;
    @Autowired
    private ContaInterface contaInterface;
    @Autowired
    private InvestimentoBusiness investimentoBusiness;
    //================================================================================
    // Adiciona um novo investimento
    //================================================================================
    @PostMapping("/investimentoAdd")
    public Object addInv(@RequestBody @Valid InvestimentoDTO investimentoDTO) {
        return investimentoBusiness.addInv(investimentoDTO);
    }
            /*investimentoDTO.setNomeInvestimento(investimentoDTO.getNomeInvestimento().toUpperCase());
            String tipoDeInvestimento = tipoDeInvestimento(investimentoDTO);
            if (tipoDeInvestimento != null) {
                List<Investimento> meusInvestimentos = investimentoInterface.findAllByNomeInvestimento(tipoDeInvestimento);
                for (Investimento meuInvestimento : meusInvestimentos) {
                    if (meuInvestimento.getConta().getNumConta().equals(conta.get().getNumConta())) {
                        float aux = meuInvestimento.getSaldo() + investimentoDTO.getSaldo();
                        investimentoDTO = meuInvestimento;
                        investimentoDTO.setSaldo(aux);
                        entrou = true;
                    }
                }
                if (listaInv.isEmpty() || !entrou) {
                    investimentoDTO.setConta(conta.get());
                }
                conta.get().setSaldoConta(conta.get().getSaldoConta() - investimentoDTO.getSaldo());
                contaInterface.save(conta.get());
                investimentoInterface.save(investimentoDTO);
            } else {
                return "Tipo de investimento não existe";
            }
            return investimentoDTO;
        } else {
            return "Não possui saldo suficiente";
        }*/


    @GetMapping("/contaInvestimento")
    public List<Investimento> retornaInvestimentos(@RequestParam Long numeroConta) {
        return investimentoBusiness.retornaInvestimentos(numeroConta);
    }

    //================================================================================
    // Retorna a lista de investimento
    //================================================================================
    @GetMapping("/retornaInvestimentos")
    public List<Investimento> retornaListaInv() {
        return investimentoBusiness.retornaListaInv();
    }

    //================================================================================
    // Devolve dinheiro
    //================================================================================
    @PutMapping("/devolveDinheiro")
    public Object tiraDinheiro(@RequestBody InvestimentoDTO investimentoDTO) {
        return investimentoBusiness.tiraDinheiro(investimentoDTO);
    }
}
