package com.niezhiliang.design.pattern.strategy;

import org.springframework.stereotype.Component;

import com.niezhiliang.design.pattern.enums.FormulaMethodEnum;

/**
 * @author niezhiliang
 * @version v
 * @date 2022/5/7 16:45
 */
@Component
public class AddStrategy extends AbstractFormulService {

    @Override
    protected FormulaMethodEnum getStrategyofFormulaMethodEnum() {
        return FormulaMethodEnum.AggregationAdd;
    }
}
