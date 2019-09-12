package rest.five.bank.InternetBanking.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Poupanca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_poupanca;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private ContaCorrente contaCorrente;
    @Column
    private float saldo;
    @Column
    private Date dt_inicio;

    //==================================================================
    // Getters e Setters
    //==================================================================
    public Long getId_poupanca() { return id_poupanca; }
    public void setId_poupanca(Long id_poupanca) { this.id_poupanca = id_poupanca; }
    public ContaCorrente getContaCorrente() { return contaCorrente; }
    public void setContaCorrente(ContaCorrente contaCorrente) { this.contaCorrente = contaCorrente; }
    public float getSaldo() { return saldo; }
    public void setSaldo(float saldo) { this.saldo = saldo; }
    public Date getDt_inicio() { return dt_inicio; }
    public void setDt_inicio(Date dt_inicio) { this.dt_inicio = dt_inicio; }

    public void geraInvestimento(){}

}
