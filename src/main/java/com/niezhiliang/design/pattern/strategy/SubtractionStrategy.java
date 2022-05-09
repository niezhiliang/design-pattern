package com.niezhiliang.design.pattern.strategy;

import com.niezhiliang.design.pattern.enums.FormulaMethodEnum;
import org.springframework.stereotype.Component;

/**
 * @author niezhiliang
 * @version v
 * @date 2022/5/7 16:45
 */
@Component
public class SubtractionStrategy extends AbstractFormulService {
    @Override
    protected FormulaMethodEnum getStrategyofFormulaMethodEnum() {
        return FormulaMethodEnum.SUBTRACTION;
    }
}
