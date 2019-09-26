package rest.five.bank.InternetBanking.model;

import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransferencia;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idContaDebito", referencedColumnName = "numConta")
    private Conta contaDebito;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idContaCredito", referencedColumnName = "numConta")
    private Conta contaCredito;

    @Column
    private Double valTransferencia;
    @Column
    private LocalDateTime dtTransferencia;

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

    public Double getValTransferencia() {
        return valTransferencia;
    }

    public void setValTransferencia(Double valTransferencia) {
        this.valTransferencia = valTransferencia;
    }

    public LocalDateTime getDtTransferencia() {
        return dtTransferencia;
    }

    public void setDtTransferencia(LocalDateTime dtTransferencia) {
        this.dtTransferencia = dtTransferencia;
    }

}
