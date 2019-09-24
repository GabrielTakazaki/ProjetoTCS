package rest.five.bank.InternetBanking.controller.dto;

import rest.five.bank.InternetBanking.model.Transferencia;

public class TransferenciaDTO {

    private Long idCreditoDTO;
    private Long idDebitoDTO;
    private float valorTransferenciaDTO;

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

    public float getValorTransferenciaDTO() {
        return valorTransferenciaDTO;
    }

    public void setValorTransferenciaDTO(float valorTransferenciaDTO) {
        this.valorTransferenciaDTO = valorTransferenciaDTO;
    }
}
