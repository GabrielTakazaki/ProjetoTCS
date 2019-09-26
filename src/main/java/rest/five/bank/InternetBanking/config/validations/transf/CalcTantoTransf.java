package rest.five.bank.InternetBanking.config.validations.transf;

import org.springframework.beans.factory.annotation.Autowired;
import rest.five.bank.InternetBanking.controller.dto.FixedClienteDTO;
import rest.five.bank.InternetBanking.controller.dto.TransferenciaDTO;
import rest.five.bank.InternetBanking.entities.ContaInterface;
import rest.five.bank.InternetBanking.entities.CreditoEspecialInterface;
import rest.five.bank.InternetBanking.model.Conta;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CalcTantoTransf implements ConstraintValidator<ValordeTransferencia, TransferenciaDTO> {

    @Autowired
    ContaInterface cInterface;
    @Autowired
    CreditoEspecialInterface credInterface;

    @Override
    public void initialize(ValordeTransferencia constraintAnnotation) {

    }

    @Override
    public boolean isValid(TransferenciaDTO transferenciaDTO, ConstraintValidatorContext constraintValidatorContext) {
        Conta optC = cInterface.findByFkIdCliente(FixedClienteDTO.returnCliente());
        return optC.getSaldoConta() >= transferenciaDTO.getValorTransferenciaDTO();
    }
}
