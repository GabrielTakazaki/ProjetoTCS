package rest.five.bank.InternetBanking.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_transferencia;
    @Column
    private Date dt_transferencia;
    @Column
    private float val_transferencia;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private ContaCorrente contaCorrenteEnviada;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private ContaCorrente contaCorrenteReceptora;

    public Long getId_transferencia() {
        return id_transferencia;
    }

    public void setId_transferencia(Long id_transferencia) {
        this.id_transferencia = id_transferencia;
    }

    public Date getDt_transferencia() {
        return dt_transferencia;
    }

    public void setDt_transferencia(Date dt_transferencia) {
        this.dt_transferencia = dt_transferencia;
    }

    public float getVal_transferencia() {
        return val_transferencia;
    }

    public void setVal_transferencia(float val_transferencia) {
        this.val_transferencia = val_transferencia;
    }

    public ContaCorrente getContaCorrenteEnviada() {
        return contaCorrenteEnviada;
    }

    public void setContaCorrenteEnviada(ContaCorrente contaCorrenteEnviada) {
        this.contaCorrenteEnviada = contaCorrenteEnviada;
    }

    public ContaCorrente getContaCorrenteReceptora() {
        return contaCorrenteReceptora;
    }

    public void setContaCorrenteReceptora(ContaCorrente contaCorrenteReceptora) {
        this.contaCorrenteReceptora = contaCorrenteReceptora;
    }
}
