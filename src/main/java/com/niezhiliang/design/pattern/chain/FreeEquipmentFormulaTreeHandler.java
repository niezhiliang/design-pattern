package com.niezhiliang.design.pattern.chain;

import com.niezhiliang.design.pattern.composite.FormulaTree;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author niezhiliang
 * @version v
 * @date 2022/5/7 11:26
 * 游离设备
 */
@Order(3)
@Component
public class FreeEquipmentFormulaTreeHandler extends AbstractFormulaTreeHandler {
    @Override
    protected List<FormulaTree> compositeFormulaTree(Long stationId) {
        FormulaTree formulaTree1 = new FormulaTree(1111L,"游离设备_1",8,1);
        FormulaTree formulaTree2 = new FormulaTree(1112L,"游离设备_2",8,1);
        return Arrays.asList(formulaTree1,formulaTree2);
    }
}
