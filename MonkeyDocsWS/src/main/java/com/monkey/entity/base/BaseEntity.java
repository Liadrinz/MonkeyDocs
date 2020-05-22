package com.monkey.entity.base;

import java.io.Serializable;
import java.lang.reflect.Field;

public class BaseEntity implements Serializable {
    public void updateFrom(BaseEntity model) {
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
