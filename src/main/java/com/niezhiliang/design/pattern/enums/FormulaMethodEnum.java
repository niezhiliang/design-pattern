package com.niezhiliang.design.pattern.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author niezhiliang
 * @version v
 * @date 2022/5/7 17:16
 */
@AllArgsConstructor
@Getter
public enum FormulaMethodEnum {

    AggregationAdd   ("CommonFormula", "AggregationAdd"),
    Electricity      ("CommonFormula", "Electricity"),
    ConstantMultiply ("CommonFormula", "ConstantMultiply"),
    ConstantsDivision("CommonFormula", "ConstantsDivision"),
    Divide("CommonFormula","Divide"),
    Max("CommonFormula","Max"),
    Avg("CommonFormula","Avg"),
    Min("CommonFormula","Min"),
    SUBTRACTION("CommonFormula","Subtraction"),

    ;

    /**
     * 方法类名称
     */
    private String clsName;
    /**
     * 方法名称
     */
    private String method;
}