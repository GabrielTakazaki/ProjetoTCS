package rest.five.bank.InternetBanking.dto;

public class DepositoDTO {
    private float valorDeposito;
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
