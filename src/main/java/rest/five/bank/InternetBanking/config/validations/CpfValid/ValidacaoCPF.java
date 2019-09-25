package rest.five.bank.InternetBanking.config.validations.CpfValid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidCpf.class})
@Documented
public @interface ValidacaoCPF {
    String message() default "{rest.five.bank.InternetBanking.controller.Form.ClienteForm.cpfCliente}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
