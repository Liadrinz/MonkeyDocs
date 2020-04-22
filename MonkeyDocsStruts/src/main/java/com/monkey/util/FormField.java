package com.monkey.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FormField {
    public String name;
    public String type;
    public  FormField() {}
    public FormField(String name, String type) {
        this.name = name;
        this.type = type;
    }
    public static List<FormField> fromClass(Class clazz, List<String> excludes) {
        Field[] fields = clazz.getDeclaredFields();
        List<FormField> formFieldList = new ArrayList<>();
        for (Field field : fields) {
            if (excludes == null || !excludes.contains(field.getName()))
                formFieldList.add(new FormField(field.getName(), field.getType().getSimpleName()));
        }
        return formFieldList;
    }
}
