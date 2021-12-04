package com.example.fitnessdb.model.validator;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchImpl implements ConstraintValidator<PasswordsMatch, Object> {

    private String firstField;
    private String secondField;
    private String message;

    @Override
    public void initialize(PasswordsMatch constraintAnnotation) {
        firstField = constraintAnnotation.firstF();
        secondField = constraintAnnotation.secondF();
        this.message = constraintAnnotation.message();

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);
        Object firstFValue = beanWrapper.getPropertyValue(firstField);
        Object secondFValue = beanWrapper.getPropertyValue(secondField);

        boolean valid;
        if (firstFValue == null || secondFValue == null) {
            valid = false;
        } else {
            valid = firstFValue.equals(secondFValue);
        }

        if (!valid) {
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(firstField)
                    .addConstraintViolation()
                    .buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(secondField)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return valid;
    }
}
