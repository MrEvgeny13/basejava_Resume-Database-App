package com.evgeny13.basejava;

import com.evgeny13.basejava.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {

    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume();
        Class<? extends Resume> clazz = r.getClass();
        Field field = clazz.getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new_uuid");

        Method method = clazz.getMethod("toString");
        Object resultMethod = method.invoke(r);
        System.out.println(resultMethod);
    }
}
