package rest.five.bank.InternetBanking.config.validations.creditoEspecial;

import rest.five.bank.InternetBanking.controller.dto.CreditoDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SaldoZerado implements ConstraintValidator<TemDinheiro, CreditoDTO> {

    @Override
    public boolean isValid(CreditoDTO creditoDTO, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
