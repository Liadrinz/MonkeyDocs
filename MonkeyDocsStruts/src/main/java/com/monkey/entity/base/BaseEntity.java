package com.monkey.entity.base;

import java.lang.reflect.Field;

public class BaseEntity<T> {
    public void updateFrom(T model) {
        Class clazz = model.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; ++i) {
                fields[i].setAccessible(true);
                fields[i].set(this, fields[i].get(model));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
