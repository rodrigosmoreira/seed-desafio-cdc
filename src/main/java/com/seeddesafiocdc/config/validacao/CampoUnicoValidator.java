package com.seeddesafiocdc.config.validacao;

import com.seeddesafiocdc.config.BeanUtil;
import com.seeddesafiocdc.repository.AutorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.FlushModeType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CampoUnicoValidator implements ConstraintValidator<CampoUnico, Object> {

    private AutorRepository repository;
    private EntityManager em;

    private CampoValidacao campo;

    @Override
    public void initialize(CampoUnico constraintAnnotation) {
        campo = constraintAnnotation.campo();
        repository = BeanUtil.getBean(AutorRepository.class);
        em = BeanUtil.getBean(EntityManager.class);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        try {
            // Forçar para a transação ser somente de leitura resetando o entity manager dessa maneira
            em.setFlushMode(FlushModeType.COMMIT);

            if(CampoValidacao.CPF.equals(campo)) {
                return repository.findByCpf((Long) value).isEmpty();
            }

            if (CampoValidacao.EMAIL.equals(campo)) {
                return repository.findByEmail((String) value).isEmpty();
            }
        } finally {
            em.setFlushMode(FlushModeType.AUTO);
        }

        return true;
    }

}