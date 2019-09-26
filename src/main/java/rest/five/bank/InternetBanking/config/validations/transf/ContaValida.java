package rest.five.bank.InternetBanking.config.validations.transf;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidaConta.class})
@Documented
public @interface ContaValida {
    String message() default "{rest.five.bank.InternetBanking.controller.dto.TransferenciaDTO}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
