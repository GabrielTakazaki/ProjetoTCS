package rest.five.bank.InternetBanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rest.five.bank.InternetBanking.entities.ContaInterface;
import rest.five.bank.InternetBanking.entities.InvestimentoInterface;
import rest.five.bank.InternetBanking.model.Conta;
import rest.five.bank.InternetBanking.model.Investimento;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/investimento")
//@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
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
                    System.out.println("TESTE");
                    for (Investimento inves : listaInv()) {
                        calcInvestimento(inves);
                    }

                    Thread.sleep(5000);
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
    public Investimento addInv(@RequestBody Investimento inv) {
        listaInv.add(inv);
        Optional<Conta> conta = contaInterface.findById(inv.getConta().getNumConta());
        conta.get().setSaldoConta(conta.get().getSaldoConta() - inv.getSaldo());
        contaInterface.save(conta.get());
        if (inv.getNomeInvestimento().equals("POUPANCA")) {
            List<Investimento> meusInvestimentos = investimentoInterface.findAllByNomeInvestimento("POUPANCA");
            for (Investimento meuInvestimento : meusInvestimentos) {
                if (meuInvestimento.getConta().getNumConta().equals(conta.get().getNumConta())) {
                    float aux = meuInvestimento.getSaldo() + inv.getSaldo();
                    inv = meuInvestimento;
                    inv.setSaldo(aux);
                }
            }
        }
        investimentoInterface.save(inv);

        if (!thread.isAlive())
            thread.start();

        return inv;
    }

    //================================================================================
    // Retorna a lista de investimento
    //================================================================================
    @GetMapping("/retornaInvestimentos")
    public List<Investimento> listaInv() {
        return investimentoInterface.findAll();
    }

    //================================================================================
    // Calcula o investimento
    //================================================================================
    public void calcInvestimento(Investimento inv) {

        if (inv.getNomeInvestimento().equals("CDI")) {
            inv.setSaldo(inv.getSaldo() * 1.05f);
        } else if (inv.getNomeInvestimento().equals("POUPANCA")) {
            inv.setSaldo(inv.getSaldo() * 1.01f);
        } else if (inv.getNomeInvestimento().equals("IPCA")) {
            inv.setSaldo(inv.getSaldo() * 1.03f);
        }
        investimentoInterface.save(inv);
    }
}
