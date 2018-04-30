package com.agile.test;

import com.agile.model.User;
import com.agile.framework.validate.EntityAssist;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class TestEntityAssistViolation {


    public static void main(String args[]) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();


        EntityAssist userAssist = new EntityAssist(User.class);
        Class<?> clazz = userAssist.setNotNull("name", "name is null!");
        userAssist.save("D:/test.class");

        System.out.println(clazz);
        if (clazz.equals(User.class)) {
            System.out.println(clazz);
        }

        try {
            //User p = (User)clazz.newInstance();
            //User p = new User();
            Object p = clazz.newInstance();
            //p.setId(1);

            //Set<?> sets = validator.validateProperty(p, "name");
            Set<?> sets = validator.validate(p);
            for (Object obj: sets) {
                ConstraintViolation<?> cv = (ConstraintViolation<?>)obj;
                String propertyPath = cv.getPropertyPath().toString();
                String message = cv.getMessage();
            }

            //User pp = new User();
        //p.setName("zhang3");
            /*
        Set<ConstraintViolation<User>> violations = validator.validate(p);
        for (ConstraintViolation<User> violation : violations) {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
        }
        */
            //BindingResult result = null;
            //userValidator.validate(p, result);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
