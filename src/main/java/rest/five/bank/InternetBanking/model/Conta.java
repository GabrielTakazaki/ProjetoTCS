package rest.five.bank.InternetBanking.model;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "seqCartao", sequenceName = "seqCartao", initialValue = 1740, allocationSize = 1)
public class Conta {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCartao")
    private Long numConta;

    @Column
    private float saldoConta;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente", unique = true)
    private Cliente fkIdCliente;



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
}
