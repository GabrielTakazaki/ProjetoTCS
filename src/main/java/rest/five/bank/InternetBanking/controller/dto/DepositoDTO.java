package rest.five.bank.InternetBanking.controller.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class DepositoDTO {
    @Positive(message = "O valor não pode ser zero ou negativo")
    private float valorDeposito;
    @NotNull(message = "Por favor, efetue o login para realizar um depósito")
    private Long idConta;

    public Long getIdConta() {
        return idConta;
    }

    public void setIdConta(Long idConta) {
        this.idConta = idConta;
    }

    public float getValorDeposito() {
        return valorDeposito;
    }

    public void setValorDeposito(float valorDeposito) {
        this.valorDeposito = valorDeposito;
    }
}
