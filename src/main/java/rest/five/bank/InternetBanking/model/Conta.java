package rest.five.bank.InternetBanking.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Conta {
    @Id
    @Column
    private Long numConta;
    @Column
    private float saldoConta;
    @OneToOne
    @MapsId
    private Cliente fkIdCliente;
    @OneToOne(mappedBy = "conta", cascade = CascadeType.ALL)
    private CreditoEspecial creditoEspecial;


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

    public void gerarNumConta(List<Conta> contasExistentes){
        this.setNumConta(1516001l);
        if(!contasExistentes.isEmpty() || contasExistentes != null) {
            for (Conta conta : contasExistentes) {
                if(conta.getNumConta() == getNumConta()) setNumConta(getNumConta() + 1);
            }
        }
    }
}
