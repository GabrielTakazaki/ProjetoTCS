package rest.five.bank.InternetBanking.controller.dto;

public class CreditoDTO {
    private Long idCliente;
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
