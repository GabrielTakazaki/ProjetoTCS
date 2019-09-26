package rest.five.bank.InternetBanking.controller.dto;

import rest.five.bank.InternetBanking.model.Cliente;

import java.time.LocalDateTime;

public class ContaDTO {
    private Long numConta;
    private Double saldoConta;
    private Cliente fkIdCliente;
    private LocalDateTime emprDateTime;
    private boolean existeEmprestimo;


    public ContaDTO(Long numConta, Double saldoConta, Cliente fkIdCliente, LocalDateTime emprDate, boolean existeEmprestimo) {
        this.numConta = numConta;
        this.saldoConta = saldoConta;
        this.fkIdCliente = fkIdCliente;
        this.emprDateTime = emprDate;
        this.existeEmprestimo = existeEmprestimo;
    }

    public LocalDateTime getEmprDateTime() {
        return emprDateTime;
    }

    public void setEmprDateTime(LocalDateTime emprDateTime) {
        this.emprDateTime = emprDateTime;
    }

    public boolean isExisteEmprestimo() {
        return existeEmprestimo;
    }

    public void setExisteEmprestimo(boolean existeEmprestimo) {
        this.existeEmprestimo = existeEmprestimo;
    }

    public Long getNumConta() {
        return numConta;
    }

    public void setNumConta(Long numConta) {
        this.numConta = numConta;
    }

    public Double getSaldoConta() {
        return saldoConta;
    }

    public void setSaldoConta(Double saldoConta) {
        this.saldoConta = saldoConta;
    }

    public Cliente getFkIdCliente() {
        return fkIdCliente;
    }

    public void setFkIdCliente(Cliente fkIdCliente) {
        this.fkIdCliente = fkIdCliente;
    }
}
