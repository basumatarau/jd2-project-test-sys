package by.htp.basumatarau.jd2TestSystem.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator
        implements ConstraintValidator<ValidEmail, String> {
    @Override
    public void initialize(ValidEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return email != null
                && email.length() < 60
                && email.matches("([\\w\\d-_]+)@([\\w\\d-_]+)[.](\\w{1,5})");
    }
}
