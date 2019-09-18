package rest.five.bank.InternetBanking.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Investimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInvestimento;
    @ManyToOne
    @JoinColumn(name = "contaId", referencedColumnName = "numConta")
    private Conta conta;
    @Column
    private String nomeInvestimento;
    @Column
    private float saldo;
    @Column
    private LocalDateTime dtInicio = LocalDateTime.now();
    //==================================================================
    // Getters e Setters
    //==================================================================


    public Long getIdInvestimento() {
        return idInvestimento;
    }

    public void setIdInvestimento(Long idInvestimento) {
        this.idInvestimento = idInvestimento;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public String getNomeInvestimento() {
        return nomeInvestimento;
    }

    public void setNomeInvestimento(String nomeInvestimento) {
        this.nomeInvestimento = nomeInvestimento.toUpperCase();
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public LocalDateTime getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(LocalDateTime dtInicio) {
        this.dtInicio = dtInicio;
    }
}
