package com.niezhiliang.design.pattern.chain;

import com.niezhiliang.design.pattern.composite.FormulaTree;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author niezhiliang
 * @version v
 * @date 2022/5/7 11:28
 * 分类分项
 */
@Order(2)
@Component
public class LevelFormulaTreeHandler extends AbstractFormulaTreeHandler {

    @Override
    protected List<FormulaTree> compositeFormulaTree(Long stationId) {
        FormulaTree equipment1 = new FormulaTree(660001L,"空调_1", 8,1);
        FormulaTree equipment2 = new FormulaTree(660002L, "空调_2",8,1);
        FormulaTree equipment3 = new FormulaTree(660003L, "空调_3",8,1);
        FormulaTree equipment4 = new FormulaTree(660004L, "空调_4",8,1);
        FormulaTree equipment5 = new FormulaTree(660005L, "空调_5",8,1);
        FormulaTree equipment6 = new FormulaTree(660006L, "空调_6",8,1);


        FormulaTree level1_1_1 = new FormulaTree(110004L, "子层级_1_1",7,1);
        level1_1_1.addChildren(Arrays.asList(equipment1,equipment2));

        FormulaTree level1_2 = new FormulaTree(110003L, "子层级_2",7,1);
        level1_2.addChildren(equipment3);

        FormulaTree level1_1 = new FormulaTree(110002L, "子层级_1",7,2);
        level1_1.addChildren(Arrays.asList(equipment4,equipment5,level1_1_1));

        FormulaTree level1 = new FormulaTree(110001L, "父层级",7,1);
        level1.addChildren(Arrays.asList(equipment6,level1_1,level1_2));

        return Arrays.asList(level1);
    }
}
