package rest.five.bank.InternetBanking.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransferencia;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idContaDebito", referencedColumnName = "idConta")
    private Conta contaDebito;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idContaCredito", referencedColumnName = "idConta")
    private Conta contaCredito;

    @Column
    private float valTransferencia;
    @Column
    private Date dtTransferencia;

    public Long getIdTransferencia() {
        return idTransferencia;
    }

    public void setIdTransferencia(Long idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    public Conta getContaDebito() {
        return contaDebito;
    }

    public void setContaDebito(Conta contaDebito) {
        this.contaDebito = contaDebito;
    }

    public Conta getContaCredito() {
        return contaCredito;
    }

    public void setContaCredito(Conta contaCredito) {
        this.contaCredito = contaCredito;
    }

    public float getValTransferencia() {
        return valTransferencia;
    }

    public void setValTransferencia(float valTransferencia) {
        this.valTransferencia = valTransferencia;
    }

    public Date getDtTransferencia() {
        return dtTransferencia;
    }

    public void setDtTransferencia(Date dtTransferencia) {
        this.dtTransferencia = dtTransferencia;
    }

}
