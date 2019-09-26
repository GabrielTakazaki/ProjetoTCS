package rest.five.bank.InternetBanking.config.validations.creditoEspecial;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {SaldoZerado.class})
@Documented
public @interface TemDinheiro {
    String message() default "{rest.five.bank.InternetBanking.controller.dto.CreditoDTO}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
