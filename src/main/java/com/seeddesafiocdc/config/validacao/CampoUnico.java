package com.seeddesafiocdc.config.validacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CampoUnicoValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CampoUnico {
    String message() default "Campo já está cadastrado";
    CampoValidacao campo();

    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}