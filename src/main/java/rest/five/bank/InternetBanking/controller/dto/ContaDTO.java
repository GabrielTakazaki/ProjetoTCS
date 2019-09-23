package rest.five.bank.InternetBanking.controller.dto;

import rest.five.bank.InternetBanking.model.Cliente;

public class ContaDTO {
    private Long numConta;
    private float saldoConta;
    private Cliente fkIdCliente;

    public ContaDTO(Long numConta, float saldoConta, Cliente fkIdCliente) {
        this.numConta = numConta;
        this.saldoConta = saldoConta;
        this.fkIdCliente = fkIdCliente;
    }
    

    public Long getNumConta() {
        return numConta;
    }

    public void setNumConta(Long numConta) {
        this.numConta = numConta;
    }

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
}
