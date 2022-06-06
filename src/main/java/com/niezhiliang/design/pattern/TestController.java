package com.niezhiliang.design.pattern;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.nacos.api.config.ConfigChangeItem;
import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.client.config.impl.ConfigChangeHandler;

/**
 * @author niezhiliang
 * @version v
 * @date 2022/6/1 16:44
 */
@RestController
public class TestController {

    private String oldContext;
    /**
     * 需要支持多层占位符解析
     */
    // @Value("${${spring.application.name}}")
    @Value("${spring.application.name}")
    public String testValue;

    @NacosValue(value = "${spring.application.name}")
    public String testValue2;

    @GetMapping("set")
    public void set(String value) {
        RefreshHolder.updateFieldValue("spring.application.name", value);
    }

    @GetMapping("get")
    public String get() {
        return testValue + "----" + testValue2;
    }

    @NacosConfigListener(dataId = "test-autorefresh")
    public void onChange(String newContent) throws Exception {
        if (oldContext == null) {
            oldContext = newContent;
        }
        System.out.println("onChange : " + newContent);

        // 判断前后配置 有哪些配置进行了变更
        Map data = ConfigChangeHandler.getInstance().parseChangeData(oldContext, newContent, "yaml");
        oldContext = newContent;
        for (Object o : data.keySet()) {
            String key = (String)o;
            ConfigChangeItem o1 = (ConfigChangeItem)data.get(o);
            RefreshHolder.updateFieldValue(key, o1.getNewValue());
        }
    }
}
