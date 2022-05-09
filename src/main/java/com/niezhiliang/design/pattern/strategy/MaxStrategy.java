package com.niezhiliang.design.pattern.strategy;

import com.niezhiliang.design.pattern.enums.FormulaMethodEnum;
import org.springframework.stereotype.Component;

/**
 * @author niezhiliang
 * @version v
 * @date 2022/5/7 16:45
 */
@Component
public class MaxStrategy extends AbstractFormulService {
    @Override
    protected FormulaMethodEnum getStrategyofFormulaMethodEnum() {
        return FormulaMethodEnum.Max;
    }

    @Override
    public String getConstant() {
        return "{\"type\": \"day\"}";
    }
}
