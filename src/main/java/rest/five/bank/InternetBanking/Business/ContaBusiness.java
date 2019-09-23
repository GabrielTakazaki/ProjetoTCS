package rest.five.bank.InternetBanking.Business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.five.bank.InternetBanking.controller.Form.ContaForm;
import rest.five.bank.InternetBanking.controller.dto.FixedClienteDTO;
import rest.five.bank.InternetBanking.dto.DepositoDTO;
import rest.five.bank.InternetBanking.entities.ClienteInterface;
import rest.five.bank.InternetBanking.entities.ContaInterface;
import rest.five.bank.InternetBanking.entities.CreditoEspecialInterface;
import rest.five.bank.InternetBanking.model.Conta;
import rest.five.bank.InternetBanking.model.CreditoEspecial;

import java.util.List;
import java.util.Optional;

@Service
public class ContaBusiness {
    @Autowired
    ClienteInterface clienteInterface;
    @Autowired
    private ContaInterface contaInterface;
    @Autowired
    private CreditoEspecialInterface creditoEspecialInteface;

    ContaForm contaForm = new ContaForm();

    public Object addConta() {

        if (contaInterface.existsByFkIdCliente(FixedClienteDTO.returnCliente())) {
            return contaInterface.findByFkIdCliente(FixedClienteDTO.returnCliente());
        }
        else {
            Conta conta = contaForm.criaConta(clienteInterface.findByCpfCliente(FixedClienteDTO.getCpfCliente()));
            return contaInterface.save(conta);
        }
    }

    public Conta buscarConta(Long id) {
        Optional<Conta> optConta = contaInterface.findById(id);
        return optConta.get();
    }

    public List<Conta> buscarContas() {
        return contaInterface.findAll();
    }

    public Object depositar(DepositoDTO dpDTO) {
        Optional<Conta> optC = contaInterface.findById(dpDTO.getIdConta());
        optC.get().setSaldoConta(optC.get().getSaldoConta() + dpDTO.getValorDeposito());

        if (creditoEspecialInteface.existsByFkIdConta(optC.get())) {
            CreditoEspecial creditoEspecial = this.creditoEspecialInteface.findByFkIdConta(optC.get());
            creditoEspecial.setValorSaldo(creditoEspecial.getValorSaldo() - dpDTO.getValorDeposito());

            if (creditoEspecial.getValorSaldo() <= 0) {
                creditoEspecial.setFkIdConta(null);
                this.creditoEspecialInteface.save(creditoEspecial);
                this.creditoEspecialInteface.delete(creditoEspecial);
            }
        }
        return "Deposito realizado com sucesso!";
    }
}
