package rest.five.bank.InternetBanking.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CreditoEspecial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCreditoEspecial;
    @OneToOne
    @MapsId
    private Conta fkIdConta;
    @Column
    private float valorDisponivel;
    @Column
    private Date dtCredito;

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

    public float getValorDisponivel() {
        return valorDisponivel;
    }

    public void setValorDisponivel(float valorDisponivel) {
        this.valorDisponivel = valorDisponivel;
    }

    public Date getDtCredito() {
        return dtCredito;
    }

    public void setDtCredito(Date dtCredito) {
        this.dtCredito = dtCredito;
    }
}
