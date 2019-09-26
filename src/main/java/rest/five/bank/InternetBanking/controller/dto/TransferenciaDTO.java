package rest.five.bank.InternetBanking.controller.dto;

import javax.validation.constraints.Positive;
import rest.five.bank.InternetBanking.config.validations.transf.ContaValida;
import rest.five.bank.InternetBanking.config.validations.transf.ValordeTransferencia;
import rest.five.bank.InternetBanking.model.Transferencia;

@ValordeTransferencia(message = "Saldo Insuficiente!")
@ContaValida(message = "Informe uma conta valida!")
public class TransferenciaDTO {

    private Long idCreditoDTO;
    private Long idDebitoDTO;
    @Positive(message = "O valor não pode ser zero nem negativo")
    private Double valorTransferenciaDTO;

    public TransferenciaDTO() {
    }

    public TransferenciaDTO(Transferencia tr) {
        this.idCreditoDTO = tr.getContaCredito().getNumConta();
        this.idDebitoDTO = tr.getContaDebito().getNumConta();
        this.valorTransferenciaDTO = tr.getValTransferencia();
    }

    public Long getIdCreditoDTO() {
        return idCreditoDTO;
    }

    public void setIdCreditoDTO(Long idCreditoDTO) {
        this.idCreditoDTO = idCreditoDTO;
    }

    public Long getIdDebitoDTO() {
        return idDebitoDTO;
    }

    public void setIdDebitoDTO(Long idDebitoDTO) {
        this.idDebitoDTO = idDebitoDTO;
    }

    public Double getValorTransferenciaDTO() {
        return valorTransferenciaDTO;
    }

    public void setValorTransferenciaDTO(Double valorTransferenciaDTO) {
        this.valorTransferenciaDTO = valorTransferenciaDTO;
    }
}
