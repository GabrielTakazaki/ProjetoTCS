package rest.five.bank.InternetBanking.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Conta {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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


    public void gerarNumConta(List<Conta> contasExistentes){
        setNumConta(1516001l);
        for (Conta conta : contasExistentes) {
            if (conta.getNumConta() >= getNumConta()) {
                setNumConta(1 + getNumConta());
            }
        }
    }
}
