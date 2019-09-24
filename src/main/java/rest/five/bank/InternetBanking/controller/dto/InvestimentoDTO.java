package rest.five.bank.InternetBanking.controller.dto;

import rest.five.bank.InternetBanking.model.Conta;
import rest.five.bank.InternetBanking.model.Investimento;

import javax.validation.constraints.PositiveOrZero;

public class InvestimentoDTO {
    private Long idInvestimento;
    private String nomeInvestimento;
    private Long conta;
    @PositiveOrZero(message = "O valor não pode ser negativo")
    private float saldo;

    public InvestimentoDTO() {
    }

    public InvestimentoDTO(Investimento investimento) {
        idInvestimento = investimento.getIdInvestimento();
        nomeInvestimento = investimento.getNomeInvestimento();
        conta = investimento.getConta().getNumConta();
        saldo = investimento.getSaldo();
    }

    public Long getIdInvestimento() {
        return idInvestimento;
    }

    public void setIdInvestimento(Long idInvestimento) {
        this.idInvestimento = idInvestimento;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public String getNomeInvestimento() {
        return nomeInvestimento;
    }

    public void setNomeInvestimento(String nomeInvestimento) {
        this.nomeInvestimento = nomeInvestimento;
    }

    public Long getConta() {
        return conta;
    }

    public void setConta(Long idConta) {
        this.conta = idConta;
    }

    public Investimento retornaInvestimento(Conta conta) {
        Investimento inv = new Investimento();
        inv.setConta(conta);
        inv.setSaldo(saldo);
        inv.setNomeInvestimento(nomeInvestimento);
        return inv;
    }
}
