package com.example.iphometask7.mappers;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class CustomMapper {
    public static <E, T> T map(E o, Class<T> dtoClazz )  {
        Field[] dtoFields = findDeclaredFields(dtoClazz);
        Field[] objFields = findDeclaredFields(o.getClass());

        T newInstance = null;
        try {
            newInstance = dtoClazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
        for (Field dtoField : dtoFields) {
            for (Field objField : objFields) {
                //Check if these fields are compatible
                if(getFieldName(dtoField).equals(getFieldName(objField)) && objField.getType() == dtoField.getType()){
                    try {
                        dtoField.set(newInstance, objField.get(o));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return newInstance;
    }

    private static String getFieldName(Field field) {
        return field.getName().toLowerCase();
    }

    private static <E> Field[] findDeclaredFields(Class<E> clazz){
        Field[] declaredFields = clazz.getDeclaredFields();
        Arrays.stream(declaredFields).forEach(field -> field.setAccessible(true));
        return declaredFields;
    }
}
