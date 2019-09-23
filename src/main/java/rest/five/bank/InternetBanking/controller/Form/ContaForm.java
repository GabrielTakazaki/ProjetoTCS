package rest.five.bank.InternetBanking.controller.Form;

import rest.five.bank.InternetBanking.model.Cliente;
import rest.five.bank.InternetBanking.model.Conta;

public class ContaForm {
    private float saldoConta;
    private Cliente fkIdCliente;

    public float getSaldoConta() {
        return saldoConta;
    }

    public void setSaldoConta(float saldoConta) {
        this.saldoConta = saldoConta;
    }

    public Cliente getFkIdCliente() {
        return fkIdCliente;
    }

    public void setFkIdCliente(Cliente fkIdCliente) {
        this.fkIdCliente = fkIdCliente;
    }

    public Conta criaConta(Cliente cliente) {
        Conta conta = new Conta();
        conta.setSaldoConta(0);
        conta.setFkIdCliente(cliente);
        return conta;
    }
}
