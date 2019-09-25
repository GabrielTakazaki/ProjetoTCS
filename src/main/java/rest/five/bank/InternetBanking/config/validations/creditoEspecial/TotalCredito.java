package rest.five.bank.InternetBanking.config.validations.creditoEspecial;

import org.springframework.beans.factory.annotation.Autowired;
import rest.five.bank.InternetBanking.controller.dto.CreditoDTO;
import rest.five.bank.InternetBanking.controller.dto.FixedClienteDTO;
import rest.five.bank.InternetBanking.entities.ContaInterface;
import rest.five.bank.InternetBanking.entities.CreditoEspecialInterface;
import rest.five.bank.InternetBanking.model.Conta;
import rest.five.bank.InternetBanking.model.CreditoEspecial;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TotalCredito implements ConstraintValidator<TotalPermitido, CreditoDTO> {

    @Autowired
    ContaInterface cInterface;
    @Autowired
    CreditoEspecialInterface credInterface;

    @Override
    public void initialize(TotalPermitido constraintAnnotation) {

    }

    @Override
    public boolean isValid(CreditoDTO creditoDTO, ConstraintValidatorContext constraintValidatorContext) {
        Conta optC = cInterface.findByFkIdCliente(FixedClienteDTO.returnCliente());
        if (credInterface.existsByFkIdConta(optC)) {
            CreditoEspecial cEsp = credInterface.findByFkIdConta(optC);
            return !(cEsp.getValorSaldo() + creditoDTO.getValorSaldo() > 600);
        } else {
            return true;
        }
    }
}
