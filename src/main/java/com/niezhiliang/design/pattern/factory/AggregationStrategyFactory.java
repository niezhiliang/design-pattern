package com.niezhiliang.design.pattern.factory;

import com.niezhiliang.design.pattern.strategy.AbstractFormulService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author niezhiliang
 * @version v
 * @date 2022/5/7 16:44
 */
@Component
public class AggregationStrategyFactory {

    @Autowired
    private Map<String, AbstractFormulService> strategyFactory;

    /**
     * 工厂获取算法策略
     * @param beanName
     * @return
     */
    public AbstractFormulService getStrategy(String beanName) {

        return strategyFactory.get(beanName);
    }
}
