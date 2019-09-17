package rest.five.bank.InternetBanking.dto;

public class TransferenciaDTO {

    private Long idCreditoDTO;
    private Long idDebitoDTO;
    private float valorTransferenciaDTO;

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
