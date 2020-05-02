package com.bw.security;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<Unique, Object> {

    @Inject
    private EntityManager entityManager;

    private String table;

    private String column;

    @Override
    public void initialize(Unique constraintAnnotation) {
        table = constraintAnnotation.table();
        column = constraintAnnotation.column();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            String sql = String.format(
                    "SELECT * FROM %s WHERE %s=:value", this.table, this.column
            );
            Object data = entityManager.createNativeQuery(sql)
                    .setParameter("value", value)
                    .getSingleResult();
            return data == null;
        } catch (NoResultException e) {
            return true;
        }
    }
}
