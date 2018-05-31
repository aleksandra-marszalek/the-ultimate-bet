package pl.coderslab.theultimatebet.validator;

import org.springframework.beans.factory.annotation.Autowired;
import pl.coderslab.theultimatebet.entity.User;
import pl.coderslab.theultimatebet.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueNameValidator implements ConstraintValidator<UniqueName, String> {

    @Autowired
    UserService userService;

        @Override
        public void initialize(UniqueName constraintAnnotation) {
        }
        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
                try {
                    User user = (userService.findByUserName(value));
                    return false;
                } catch (NullPointerException e) {
                    return true;
                }


        }
}
