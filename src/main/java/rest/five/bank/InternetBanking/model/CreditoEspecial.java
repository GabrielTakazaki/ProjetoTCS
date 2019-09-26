package rest.five.bank.InternetBanking.model;

import java.util.Date;
import javax.persistence.*;

@Entity
public class CreditoEspecial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCreditoEspecial;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idConta", referencedColumnName = "numConta")
    private Conta fkIdConta;

    @Column
    private Double valorSaldo;
    @Column
    private Date dtCredito = new Date();

    public Long getIdCreditoEspecial() {
        return idCreditoEspecial;
    }

    public void setIdCreditoEspecial(Long idCreditoEspecial) {
        this.idCreditoEspecial = idCreditoEspecial;
    }

    public Conta getFkIdConta() {
        return fkIdConta;
    }

    public void setFkIdConta(Conta fkIdConta) {
        this.fkIdConta = fkIdConta;
    }

    public Double getValorSaldo() {
        return valorSaldo;
    }

    public void setValorSaldo(Double saldoConta) {
        this.valorSaldo = (double) (int) (saldoConta * 100) / 100;
    }

    public Date getDtCredito() {
        return dtCredito;
    }

    public void setDtCredito(Date dtCredito) {
        this.dtCredito = dtCredito;
    }
}
