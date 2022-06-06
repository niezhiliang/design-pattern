package com.niezhiliang.design.pattern;

import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.spring.beans.factory.annotation.AbstractAnnotationBeanPostProcessor;

/**
 * @author niezhiliang
 * @version v
 * @date 2022/6/1 16:03
 */
@Component
@Order
public class MyAnnotationPostProcess extends AbstractAnnotationBeanPostProcessor implements EnvironmentAware {

    @Override
    protected Object doGetInjectedBean(AnnotationAttributes annotationAttributes, Object o, String s, Class<?> aClass,
        InjectionMetadata.InjectedElement injectedElement) throws Exception {
        return annotationAttributes.get("value");
    }

    public MyAnnotationPostProcess() {
        super(Value.class, NacosValue.class);
    }

    @Override
    protected String buildInjectedObjectCacheKey(AnnotationAttributes annotationAttributes, Object o, String s,
        Class<?> aClass, InjectionMetadata.InjectedElement injectedElement) {
        Field field = (Field)injectedElement.getMember();
        String key = null;
        if (field.isAnnotationPresent(Value.class)) {

            key = field.getAnnotation(Value.class).value();
        } else if (field.isAnnotationPresent(NacosValue.class)) {
            key = field.getAnnotation(NacosValue.class).value();
        }
        int startIndex = key.indexOf("${");
        int endIndex = findPlaceholderEndIndex(key, startIndex);
        key = key.substring(startIndex + 2, endIndex);
        RefreshHolder.addFieldInstance(key, field, o);
        return injectedElement.getMember().getName();
    }

    private int findPlaceholderEndIndex(CharSequence buf, int startIndex) {
        int index = startIndex + 2; // ${长度
        int withinNestedPlaceholder = 0;
        while (index < buf.length()) {
            if (StringUtils.substringMatch(buf, index, "}")) {
                if (withinNestedPlaceholder > 0) {
                    withinNestedPlaceholder--;
                    index = index + 1;
                } else {
                    return index;
                }
            } else if (StringUtils.substringMatch(buf, index, "{")) {
                withinNestedPlaceholder++;
                index = index + 1;
            } else {
                index++;
            }
        }
        return -1;
    }
}
