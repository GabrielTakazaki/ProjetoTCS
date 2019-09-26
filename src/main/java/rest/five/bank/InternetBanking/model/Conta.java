package rest.five.bank.InternetBanking.model;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column
    private LocalDateTime emprDateTime;

    @Column
    private boolean existeEmprestimo = false;

    public LocalDateTime getEmprDateTime() {
        return emprDateTime;
    }

    public void setEmprDateTime(LocalDateTime emprDateTime) {
        this.emprDateTime = emprDateTime;
    }

    public boolean isExisteEmprestimo() {
        return existeEmprestimo;
    }

    public void setExisteEmprestimo(boolean existeEmprestimo) {
        this.existeEmprestimo = existeEmprestimo;
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
}