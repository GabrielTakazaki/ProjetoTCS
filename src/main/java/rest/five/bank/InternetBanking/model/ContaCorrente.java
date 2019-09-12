package rest.five.bank.InternetBanking.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ContaCorrente {
    @Id
    @GeneratedValue
    private Long id_conta_corrent;
    @Column
    private Long num_conta_corrente;
    @Column
    private float saldo_conta_corrente;
    @Column
    private Long id_transf;
    @Column
    private float id_poupanca;

    public Long getId_conta_corrent() {
        return id_conta_corrent;
    }

    public void setId_conta_corrent(Long id_conta_corrent) {
        this.id_conta_corrent = id_conta_corrent;
    }

    public Long getNum_conta_corrente() {
        return num_conta_corrente;
    }

    public void setNum_conta_corrente(Long num_conta_corrente) {
        this.num_conta_corrente = num_conta_corrente;
    }

    public float getSaldo_conta_corrente() {
        return saldo_conta_corrente;
    }

    public void setSaldo_conta_corrente(float saldo_conta_corrente) {
        this.saldo_conta_corrente = saldo_conta_corrente;
    }

    public Long getId_transf() {
        return id_transf;
    }

    public void setId_transf(Long id_transf) {
        this.id_transf = id_transf;
    }

    public float getId_poupanca() {
        return id_poupanca;
    }

    public void setId_poupanca(float id_poupanca) {
        this.id_poupanca = id_poupanca;
    }
}
