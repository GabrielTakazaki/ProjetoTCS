package rest.five.bank.InternetBanking.controller.dto;

import rest.five.bank.InternetBanking.config.validations.creditoEspecial.TotalPermitido;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;

@TotalPermitido(message = "Limite do empréstimo atingido")
public class CreditoDTO {
    private Long idCliente;
    @Max(value = 600, message = "O valor não pode ser superior a R$600,00")
    @Positive(message = "O valor não pode ser zero ou negativo")
    private float valorSaldo;

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public float getValorSaldo() {
        return valorSaldo;
    }

    public void setValorSaldo(float valorSaldo) {
        this.valorSaldo = valorSaldo;
    }
}
