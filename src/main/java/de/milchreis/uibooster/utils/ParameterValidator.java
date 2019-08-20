package de.milchreis.uibooster.utils;

public class ParameterValidator {

    public static void nonNull(Object object) {
        if(object == null)
            throw new IllegalArgumentException("The given argument is null");
    }
}
