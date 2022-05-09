package com.niezhiliang.design.pattern.strategy;

import com.niezhiliang.design.pattern.composite.FormulaTree;
import com.niezhiliang.design.pattern.entity.Formula;
import com.niezhiliang.design.pattern.enums.FormulaMethodEnum;
import org.apache.tomcat.util.buf.StringUtils;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author niezhiliang
 * @version v
 * @date 2022/5/7 16:45
 */
public abstract class AbstractFormulService {

    /**
     * 生成公式逻辑由具体的算法策略来实现
     *
     * @param formulaList 所有公式
     * @param tree        生成公式的节点
     * @param stationId   站点id
     * @param meterName   生成的计算项编码
     */
    public void generalFormula(List<Formula> formulaList, FormulaTree tree, Long stationId, String meterName) {
        //下一层级的meterName累加
        String pointIds = getFormulaParamPoints(tree, meterName);
        Float tag = getFormulaTag();
        String constant = getConstant();
        boolean isRealTime = getIsRealTime();
        Formula formula = new Formula();
        formulaList.add(formula);
        formula.setParameterPointIds(pointIds);
        formula.setMonitoredObjectId(tree.getId());
        formula.setMetricCode(meterName);
        formula.setStationId(stationId);
        formula.setConstants(constant);
        formula.setTag(tag);
        formula.setIsRealtime(isRealTime);
        formulaList.add(formula);
        // 获取对应的算法
        FormulaMethodEnum methodEnum = this.getStrategyofFormulaMethodEnum();

        formula.setMethod(methodEnum.getMethod());
        formula.setClassName(methodEnum.getClsName());
    }

    /**
     * 获取参数
     *
     * @param tree      生成公式的节点
     * @param meterName 公式code
     * @return
     */
    protected String getFormulaParamPoints(FormulaTree tree, String meterName) {
        List<Long> nodeIdList = null;
        // 聚合 取所有子节点
        if (tree.getLevelCalcType().equals(1)) {
            nodeIdList = tree.getChildTreeList().stream().map(FormulaTree::getId)
                    .collect(Collectors.toList());
            // 统计 只取子节点中的设备节点
        } else {
            nodeIdList = tree.getChildTreeList().stream().filter(e -> e.getType() == 8)
                    .map(FormulaTree::getId).collect(Collectors.toList());
        }
        // TODO 通过meterNasme从ThreadLocal获取CacheManager 中获对应节点的point点 这里模拟一下
        List<String> pointIds = nodeIdList.stream().map(String::valueOf).collect(Collectors.toList());
        return StringUtils.join(pointIds, ',');
    }

    /**
     * 获取对应算法的枚举
     *
     * @return
     */
    protected abstract FormulaMethodEnum getStrategyofFormulaMethodEnum();


    /**
     * 生成公式需要用到常量，抽象类实现通用的constant
     * 如需修改，让子类重写该方法
     *
     * @return
     */
    public String getConstant() {

        return null;
    }

    /**
     * 获取统计项公式是否实时计算
     *
     * @return
     */
    protected boolean getIsRealTime() {
        // TODO 从ThreadLocal获取CacheManager 中获取计算项是否实时计算
        return new Random().nextBoolean();
    }

    /**
     * 获取当前公式的tag
     *
     * @return
     */
    public Float getFormulaTag() {
        /**
         //10位上的数
         Float tenDigit = getTagTenDigit();
         //采集项 默认取1
         Float singleDigit = getTagSingleDigit();
         //tag的十位和个位
         Float tagDefault = tenDigit + singleDigit;
         */
        return 11F;
    }
}