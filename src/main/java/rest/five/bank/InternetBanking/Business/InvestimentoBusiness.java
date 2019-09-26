package rest.five.bank.InternetBanking.Business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import rest.five.bank.InternetBanking.controller.dto.InvestimentoDTO;
import rest.five.bank.InternetBanking.entities.ContaInterface;
import rest.five.bank.InternetBanking.entities.CreditoEspecialInterface;
import rest.five.bank.InternetBanking.entities.InvestimentoInterface;
import rest.five.bank.InternetBanking.model.Conta;
import rest.five.bank.InternetBanking.model.Investimento;

@Service
@EnableScheduling
public class InvestimentoBusiness {
    @Autowired
    InvestimentoInterface investimentoInterface;
    @Autowired
    private ContaInterface contaInterface;
    @Autowired
    private CreditoEspecialInterface cdInterface;

    //================================================================================
    // Realiza um novo investimento
    //================================================================================
    @Transactional
    public Object addInv(InvestimentoDTO investimentoDTO) {
        Optional<Conta> optConta = contaInterface.findById(investimentoDTO.getConta());
        if (existeInvestimento(investimentoDTO)) {
            if (validaInvestimento(investimentoDTO, optConta.get())) {
                Investimento investimento = investimentoDTO.retornaInvestimento(optConta.get());
                investimentoInterface.save(investimento);
            } else {
                return "Seu saldo é insuficiente";
            }
        }
        optConta.get().setSaldoConta(optConta.get().getSaldoConta() - investimentoDTO.getSaldo());
        return null;
    }

    //================================================================================
    // Verifica se existe o investimento
    //================================================================================
    @Transactional
    public boolean existeInvestimento(InvestimentoDTO investimentoDTO) {
        for (Investimento i : investimentoInterface.findAll()) {
            if (i.getConta().getNumConta().equals(investimentoDTO.getConta())) {
                if (i.getNomeInvestimento().equals(investimentoDTO.getNomeInvestimento().toUpperCase())) {
                    i.setSaldo(i.getSaldo() + investimentoDTO.getSaldo());
                    return false;
                }
            }
        }
        return true;
    }

    private Boolean validaInvestimento(InvestimentoDTO investimentoDTO, Conta conta) {
        return conta.getSaldoConta() >= investimentoDTO.getSaldo();
    }

    //================================================================================
    // Chama os valores para atualizar o investimento
    //================================================================================
    @Scheduled(cron = "0/5 * * * * ?")
    public void realizaInv() {
        List<Investimento> listaInv = retornaListaInv();
        for (Investimento inves : listaInv) {
            calcInvestimento(inves);
        }
    }

    //================================================================================
    // Retorna a lista de investimento
    //================================================================================
    public List<Investimento> retornaListaInv() {
        return investimentoInterface.findAll();
    }

    //================================================================================
    // Atualiza os valores dos investimento
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
        investimentoInterface.save(inv);
    }

    //================================================================================
    // Deleta investimento
    //================================================================================
    public void deteleInv(Long id) {
        investimentoInterface.delete(investimentoInterface.findById(id).get());
    }

    //================================================================================
    // Devolve dinheiro
    //================================================================================
    @Transactional
    public Object tiraDinheiro(InvestimentoDTO invDto) {
        Optional<Investimento> inv = investimentoInterface.findById(invDto.getIdInvestimento());
        inv.get().setSaldo(inv.get().getSaldo() - invDto.getSaldo());
        Optional<Conta> optC = contaInterface.findById(inv.get().getConta().getNumConta());
        /*Float aux = invDto.getSaldo();
        if (optC.get().isExisteEmprestimo()) {
                CreditoEspecial cd = cdInterface.findByFkIdConta(optC.get());
                float aux2 = invDto.getSaldo();
                invDto.setSaldo(cd.getValorSaldo() - invDto.getSaldo());
                cd.setValorSaldo(cd.getValorSaldo() - aux2);
            if (cd.getValorSaldo() <= 0) {
                optC.get().setSaldoConta(0d);
                optC.get().setExisteEmprestimo(false);
                optC.get().setEmprDateTime(null);
                cd.setFkIdConta(null);
                cdInterface.delete(cd);
            }
        }
        if (invDto.getSaldo() < 0)
            optC.get().setSaldoConta(optC.get().getSaldoConta() - invDto.getSaldo());
        else*/
        optC.get().setSaldoConta(optC.get().getSaldoConta() + invDto.getSaldo());
        investimentoInterface.delete(inv.get());
        return "Investimento retirando com sucesso";
    }

    //================================================================================
    // Lista os investimentos e retorna para o front
    //================================================================================
    public List<InvestimentoDTO> retornaInvestimentos(Long numeroConta) {
        Optional<Conta> conta = contaInterface.findById(numeroConta);
        List<InvestimentoDTO> listInvestimento = new ArrayList<>();
        investimentoInterface.findAllByConta(conta.get()).forEach(investimento -> {
            listInvestimento.add(new InvestimentoDTO(investimento));
        });

        return listInvestimento;
    }
}
