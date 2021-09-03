package com.example.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private String firstFieldName;
    private String secondFieldName;
    private String message;

    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        boolean valid = true;
        try {
            final Object firstObj = BeanUtils.getProperty(value, firstFieldName);
            final Object secondObj = BeanUtils.getProperty(value, secondFieldName);

            valid = firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
        } catch (final Exception ignored) {
            logger.error("Could not retrieve properties from object to validate. Ingoring.");
        }

        if (!valid) {
            context.buildConstraintViolationWithTemplate(message).addPropertyNode(firstFieldName)
                    .addConstraintViolation().disableDefaultConstraintViolation();
        }

        return valid;
    }
}
