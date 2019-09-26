package rest.five.bank.InternetBanking.Business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.five.bank.InternetBanking.controller.Form.ContaForm;
import rest.five.bank.InternetBanking.controller.dto.DepositoDTO;
import rest.five.bank.InternetBanking.controller.dto.FixedClienteDTO;
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

    public Conta addConta() {

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

    public void depositar(DepositoDTO dpDTO) {
        Optional<Conta> optC = contaInterface.findById(dpDTO.getIdConta());

        if (creditoEspecialInteface.existsByFkIdConta(optC.get())) {
            CreditoEspecial creditoEspecial = this.creditoEspecialInteface.findByFkIdConta(optC.get());
            creditoEspecial.setValorSaldo(creditoEspecial.getValorSaldo() - dpDTO.getValorDeposito());

            optC.get().setSaldoConta(optC.get().getSaldoConta() - dpDTO.getValorDeposito());
            deletaCredito(creditoEspecial, optC.get());
        } else {
            optC.get().setSaldoConta(optC.get().getSaldoConta() + dpDTO.getValorDeposito());
        }
    }

    public void deletaCredito(CreditoEspecial creditoEspecial, Conta conta) {
        if (creditoEspecial.getValorSaldo() <= 0) {
            conta.setEmprDateTime(null);
            conta.setExisteEmprestimo(false);
            creditoEspecial.setFkIdConta(null);
            this.creditoEspecialInteface.save(creditoEspecial);
            this.creditoEspecialInteface.delete(creditoEspecial);
        }
    }
}
