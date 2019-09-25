package rest.five.bank.InternetBanking.Business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.five.bank.InternetBanking.controller.dto.CreditoDTO;
import rest.five.bank.InternetBanking.entities.ContaInterface;
import rest.five.bank.InternetBanking.entities.CreditoEspecialInterface;
import rest.five.bank.InternetBanking.model.Conta;
import rest.five.bank.InternetBanking.model.CreditoEspecial;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CreditoEspecialBusiness {

    @Autowired
    CreditoEspecialInterface cdInterface;
    @Autowired
    ContaInterface contaInterface;

    @Transactional
    public void save(CreditoDTO cdEspecial) {
        Optional<Conta> optC = contaInterface.findById(cdEspecial.getIdCliente());
        Conta conta = optC.get();
        if (cdInterface.existsByFkIdConta(conta)) {
            atualizaCredito(conta, cdEspecial);
        } else {
            criaCredito(conta, cdEspecial);
        }
    }

    private void criaCredito(Conta conta, CreditoDTO cdEspecial) {
        CreditoEspecial cdEspecialNew = new CreditoEspecial();
        cdEspecialNew.setFkIdConta(conta);
        cdEspecialNew.setValorSaldo(cdEspecial.getValorSaldo());
        conta.setSaldoConta(conta.getSaldoConta() - cdEspecial.getValorSaldo());
        cdInterface.save(cdEspecialNew);
    }


    @Transactional
    private void atualizaCredito(Conta conta, CreditoDTO cdEspecial) {
        CreditoEspecial cdEsp = cdInterface.findByFkIdConta(conta);
        cdEsp.setValorSaldo(cdEspecial.getValorSaldo() + cdEsp.getValorSaldo());
        conta.setSaldoConta(conta.getSaldoConta() - cdEspecial.getValorSaldo());
    }

    public CreditoEspecial findCredito(Long id) {
        Optional<CreditoEspecial> optC = cdInterface.findById(id);
        return optC.get();
    }

    public Object updateCredito(CreditoEspecial maisCredito) {
        Optional<Conta> optC = contaInterface.findById(maisCredito.getFkIdConta().getNumConta());
        CreditoEspecial optCred = cdInterface.findByFkIdConta(optC.get());
        if ((optCred.getValorSaldo() + maisCredito.getValorSaldo()) < 600) {
            optCred.setValorSaldo(optCred.getValorSaldo() + maisCredito.getValorSaldo());
            return cdInterface.save(optCred);
        } else {
            return "Atigido o limite do crédito especial";
        }
    }
}
