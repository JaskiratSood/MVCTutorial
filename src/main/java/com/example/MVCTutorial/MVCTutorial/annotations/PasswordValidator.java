package com.example.MVCTutorial.MVCTutorial.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PasswordValidator implements ConstraintValidator<PasswordValidation, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        boolean lowerChar = false;
        boolean upperChar = false;
        boolean specialChar = false;
        List<Character> specialCharList = Arrays.asList('@','$','#','%','&');

        int leng = s.length();

        if( s.length()<=6 || 10 <= s.length())
            return false;

        for (int i =0;i<s.length();i++){
            char chars = s.charAt(i);
            if(Character.isLowerCase(chars))
                lowerChar = true;
            else if(Character.isUpperCase(chars))
                upperChar = true;
            else if(specialCharList.contains(chars))
                specialChar =true;
        }
        if(lowerChar && upperChar && specialChar)
            return  true;

        return false;
    }
}
