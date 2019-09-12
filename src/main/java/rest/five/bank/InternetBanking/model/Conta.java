package rest.five.bank.InternetBanking.model;

import javax.persistence.*;

@Entity
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConta;
    @Column
    private Long numConta;
    @Column
    private float saldoConta;
    @OneToOne
    @MapsId
    private Cliente fkIdCliente;
    @OneToOne(mappedBy = "conta", cascade = CascadeType.ALL)
    private CreditoEspecial creditoEspecial;

    public Long getIdConta() {
        return idConta;
    }

    public void setIdConta(Long idConta) {
        this.idConta = idConta;
    }

    public Long getNumConta() {
        return numConta;
    }

    public void setNumConta(Long numConta) {
        this.numConta = numConta;
    }

    public float getSaldoConta() {
        return saldoConta;
    }

    public void setSaldoConta(float saldoConta) {
        this.saldoConta = saldoConta;
    }

    public Cliente getFkIdCliente() {
        return fkIdCliente;
    }

    public void setFkIdCliente(Cliente fkIdCliente) {
        this.fkIdCliente = fkIdCliente;
    }

    public CreditoEspecial getCreditoEspecial() {
        return creditoEspecial;
    }

    public void setCreditoEspecial(CreditoEspecial creditoEspecial) {
        this.creditoEspecial = creditoEspecial;
    }
}
