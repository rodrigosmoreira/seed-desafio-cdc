package com.seeddesafiocdc.config.validacao;

import com.seeddesafiocdc.exception.AutorNaoCadastradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CampoErroHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<CampoErroDto> handle(MethodArgumentNotValidException exception) {
        return exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::getCampoErro)
                .toList();
    }

    private CampoErroDto getCampoErro(FieldError erro) {
        return new CampoErroDto(erro.getField(), messageSource.getMessage(erro, LocaleContextHolder.getLocale()));
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public List<CampoErroDto> handle(ConstraintViolationException exception) {
        return exception
                .getConstraintViolations()
                .stream()
                .map(CampoErroHandler::getCampoErro)
                .toList();
    }

    private static CampoErroDto getCampoErro(ConstraintViolation<?> erro) {
        return new CampoErroDto(erro.getPropertyPath().toString(), erro.getMessageTemplate());
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(AutorNaoCadastradoException.class)
    public List<CampoErroDto> handle(AutorNaoCadastradoException exception) {
        return new ArrayList<>(List.of(new CampoErroDto("email", "não foi encontrando um autor com o email informado")));
    }

}
