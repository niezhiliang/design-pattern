package com.niezhiliang.design.pattern;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.niezhiliang.design.pattern.tools.StreamTools;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @author niezhiliang
 * @version v
 * @date 2022/6/1 16:31
 */
public class RefreshHolder {

    private final static Map<String, List<FieldInstance>> map = new HashMap<>();

    @Data
    @AllArgsConstructor
    @Getter
    static class FieldInstance {
        Object o;

        Field field;
    }

    public static void addFieldInstance(String key, Field field, Object o) {
        List<FieldInstance> fieldInstances = map.get(key);
        if (StreamTools.isEmpty(fieldInstances)) {
            fieldInstances = new ArrayList<>();
        }
        fieldInstances.add(new FieldInstance(o, field));
        map.put(key, fieldInstances);
    }

    public static void updateFieldValue(String key, Object value) {
        try {
            List<FieldInstance> fieldInstances = map.get(key);
            for (FieldInstance fieldInstance : fieldInstances) {
                fieldInstance.field.set(fieldInstance.o, value);
            }
        } catch (Exception e) {

        }
    }
}
