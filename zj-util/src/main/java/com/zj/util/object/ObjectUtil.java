package com.zj.util.object;

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

class User{
    private String name;
    private int age;
    @CopyIgnore
    private boolean sex;

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }
}

class UserInfo{
    private String name;
    private int age;
    @CopyIgnore
    private boolean sex;
    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }
}

class UserDesc{
    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
