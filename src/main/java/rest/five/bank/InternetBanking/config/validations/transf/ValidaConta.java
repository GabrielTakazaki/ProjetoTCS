package rest.five.bank.InternetBanking.config.validations.transf;

import org.springframework.beans.factory.annotation.Autowired;
import rest.five.bank.InternetBanking.controller.dto.FixedClienteDTO;
import rest.five.bank.InternetBanking.controller.dto.TransferenciaDTO;
import rest.five.bank.InternetBanking.entities.ContaInterface;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidaConta implements ConstraintValidator<ContaValida, TransferenciaDTO> {

    @Autowired
    ContaInterface cInteface;

    @Override
    public boolean isValid(TransferenciaDTO transferenciaDTO, ConstraintValidatorContext constraintValidatorContext) {
        if (transferenciaDTO.getIdCreditoDTO().equals(cInteface.findByFkIdCliente((FixedClienteDTO.returnCliente())).getNumConta())) {
            return false;
        } else return cInteface.existsById(transferenciaDTO.getIdCreditoDTO());
    }
}
