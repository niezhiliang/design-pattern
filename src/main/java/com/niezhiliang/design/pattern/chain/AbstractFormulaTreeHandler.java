package com.niezhiliang.design.pattern.chain;

import com.niezhiliang.design.pattern.composite.FormulaTree;

import java.util.List;

/**
 * @author niezhiliang
 * @version v
 * @date 2022/5/7 11:24
 */
public abstract class AbstractFormulaTreeHandler {

    /**
     * 组装树结构模板方法
     * @param stationId 站点id
     * @return
     */
    protected abstract List<FormulaTree> compositeFormulaTree(Long stationId);


    /**
     * 子类可以重写该方法来决定是否执行该处理器
     * @return
     */
    protected boolean isInvoke() {
        return true;
    }
}
