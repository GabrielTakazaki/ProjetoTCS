package rest.five.bank.InternetBanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rest.five.bank.InternetBanking.dto.InvestimentoDTO;
import rest.five.bank.InternetBanking.entities.ContaInterface;
import rest.five.bank.InternetBanking.entities.InvestimentoInterface;
import rest.five.bank.InternetBanking.model.Conta;
import rest.five.bank.InternetBanking.model.Investimento;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/investimento")
@CrossOrigin
public class InvestimentoController {
    @Autowired
    private InvestimentoInterface investimentoInterface;
    @Autowired
    private ContaInterface contaInterface;

    private List<Investimento> listaInv = new ArrayList<>();
    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(60000);
                    listaInv = retornaListaInv();
                    System.out.println("TESTE");
                    for (Investimento inves : listaInv) {
                        calcInvestimento(inves);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    });

    //================================================================================
    // Adiciona um novo investimento
    //================================================================================
    @PostMapping("/investimentoAdd")
    public Object addInv(@RequestBody Investimento inv) {
        Optional<Conta> conta = contaInterface.findById(inv.getConta().getNumConta());
        if (conta.get().getSaldoConta() >= inv.getSaldo()) {
            boolean entrou = false;
            inv.setNomeInvestimento(inv.getNomeInvestimento().toUpperCase());
            String tipoDeInvestimento = tipoDeInvestimento(inv);
            if (tipoDeInvestimento != null) {
                List<Investimento> meusInvestimentos = investimentoInterface.findAllByNomeInvestimento(tipoDeInvestimento);
                for (Investimento meuInvestimento : meusInvestimentos) {
                    if (meuInvestimento.getConta().getNumConta().equals(conta.get().getNumConta())) {
                        float aux = meuInvestimento.getSaldo() + inv.getSaldo();
                        inv = meuInvestimento;
                        inv.setSaldo(aux);
                        entrou = true;
                    }
                }
                if (listaInv.isEmpty() || !entrou) {
                    inv.setConta(conta.get());
                }
                conta.get().setSaldoConta(conta.get().getSaldoConta() - inv.getSaldo());
                contaInterface.save(conta.get());
                investimentoInterface.save(inv);
            } else {
                return "Tipo de investimento não existe";
            }

            if (!thread.isAlive())
                thread.start();

            return inv;
        } else {
            return "Não possui saldo suficiente";
        }
    }

    public String tipoDeInvestimento (Investimento investimento) {
        if (investimento.getNomeInvestimento().equals("POUPANCA")) {
            return "POUPANCA";
        }
        else if (investimento.getNomeInvestimento().equals("CDI")) {
            return "CDI";
        }
        else if (investimento.getNomeInvestimento().equals("IPCA")) {
            return "IPCA";
        }
        else return null;
    }

    @GetMapping("/contaInvestimento")
    public List<Investimento> retornaInvestimentos(@RequestParam Long numeroConta) {
        Optional<Conta> conta = contaInterface.findById(numeroConta);
        List<Investimento> listInvestimento = investimentoInterface.findAllByConta(conta.get());
        return listInvestimento;
    }

    //================================================================================
    // Retorna a lista de investimento
    //================================================================================
    @GetMapping("/retornaInvestimentos")
    public List<Investimento> retornaListaInv() {
        return investimentoInterface.findAll();
    }

    //================================================================================
    // Calcula o investimento
    //================================================================================
    @Transactional
    public void calcInvestimento(Investimento inv) {

        if (inv.getNomeInvestimento().equals("CDI")) {
            inv.setSaldo(inv.getSaldo() * 1.05f);
        } else if (inv.getNomeInvestimento().equals("POUPANCA")) {
            inv.setSaldo(inv.getSaldo() * 1.01f);
        } else if (inv.getNomeInvestimento().equals("IPCA")) {
            inv.setSaldo(inv.getSaldo() * 1.03f);
        }
    }

    @PutMapping("/devolveDinheiro")
    @Transactional
    public Object tiraDinheiro(@RequestBody InvestimentoDTO invDto) {
        Optional<Investimento> inv = investimentoInterface.findById(invDto.getIdInvestimento());
        inv.get().setSaldo(inv.get().getSaldo() - invDto.getSaldo());
        Optional<Conta> optC = contaInterface.findById(inv.get().getConta().getNumConta());
        optC.get().setSaldoConta(optC.get().getSaldoConta() + invDto.getSaldo());
        if (inv.get().getSaldo() <= 0) {
            investimentoInterface.delete(inv.get());
        }
        return "Investimento retirando com sucesso";
    }
}
