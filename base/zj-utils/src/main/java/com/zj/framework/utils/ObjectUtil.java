package com.zj.framework.utils;

import com.zj.framework.utils.object.CopyIgnore;

import java.lang.reflect.Field;

public class ObjectUtil {
    public static void copyProperty(Object from,Object to){
        Class fromClass = from.getClass();
        Class toClass = to.getClass();
        Field[] fromField = fromClass.getDeclaredFields();
        for (Field field : fromField) {
            field.setAccessible(true);
            if(field.isAnnotationPresent(CopyIgnore.class)){
                continue;
            }
            String fieldName = field.getName();
            Field toField = getField(toClass,fieldName);
            if(null == toField){
                continue;
            }
            try {
                toField.set(to,field.get(from));
            } catch (IllegalAccessException e) {
                System.out.println("set field error:"+fieldName);
            }
        }
    }

    private static Field getField(Class toClass,String fieldName){
        Field field = null;
        try {
            field = toClass.getDeclaredField(fieldName);
            if(null == field || field.isAnnotationPresent(CopyIgnore.class)){
                return null;
            }
        }catch (Exception e){
            System.out.println("get field error:"+fieldName);
        }
        if(null != field){
            field.setAccessible(true);
        }
        return field;
    }

}

