package com.niezhiliang.design.pattern.chain;

import com.niezhiliang.design.pattern.composite.FormulaTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.OrderComparator;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author niezhiliang
 * @version v
 * @date 2022/5/7 11:26
 * 组装节点树链
 */
@Service
public class CompositeFormulaTreeChain {

    @Autowired
    private List<AbstractFormulaTreeHandler> formulaTreeHandlers;

    /**
     * 调用责任链处理生成公式树请求
     * @param stationId
     * @return
     */
    public List<FormulaTree> buildStationFormulaTree(Long stationId) {
        List<FormulaTree> result = new ArrayList<>(10);
        // 遍历责任链所有的处理器
        for (AbstractFormulaTreeHandler handler : formulaTreeHandlers) {
            // 判断是否需要执行该处理器
            if (handler.isInvoke()) {
                List<FormulaTree> trees = handler.compositeFormulaTree(stationId);
                result.addAll(trees);
            }
        }
        return result;
    }

    /**
     * 获取单棵树所有的节点
     * @param tree
     * @return
     */
    public List<FormulaTree> getOneTreeAllNode(FormulaTree tree) {
        if (tree == null) {
            return new ArrayList<>();
        }
        List<FormulaTree> result = new ArrayList<>(10);
        //非设备层级继续遍历
        if (!tree.getType().equals(8) && !CollectionUtils.isEmpty(tree.getChildTreeList())) {
            //排序是为了先添加设备 再添加层级
            for (FormulaTree formulaTree : tree.getChildTreeList()) {
                //添加后续节点的子节点
                result.addAll(getOneTreeAllNode(formulaTree));
            }
        }
        result.add(tree);
        return result;
    }

    /**
     * 新增处理器
     * @param handler
     */
    private void addHandler(AbstractFormulaTreeHandler handler) {
        formulaTreeHandlers.add(handler);
    }


    /**
     * 按给定Order顺序构建责任链
     */
    @PostConstruct
    private void buildChain() {
        // 按给定的order顺序排序,Spring默认就会按照order排序，其实这个代码都没用
        formulaTreeHandlers.sort(OrderComparator.INSTANCE);
    }

}
