import com.niezhiliang.design.pattern.Application;
import com.niezhiliang.design.pattern.chain.CompositeFormulaTreeChain;
import com.niezhiliang.design.pattern.composite.FormulaTree;
import com.niezhiliang.design.pattern.entity.Formula;
import com.niezhiliang.design.pattern.factory.AggregationStrategyFactory;
import com.niezhiliang.design.pattern.strategy.AbstractFormulService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author niezhiliang
 * @version v
 * @date 2022/5/7 15:52
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class CompositeAndChainTest {
    @Autowired
    private CompositeFormulaTreeChain formulaTreeChain;
    @Autowired
    private AggregationStrategyFactory strategyFactory;
    /**
     * 算法策略
     */
    private static final List<String> STRATEGY_NAME_LIST = Arrays.asList("addStrategy", "maxStrategy", "subtractionStrategy");

    @Test
    public void compositeAndChainTest() {
        List<FormulaTree> formulaTrees = formulaTreeChain.buildStationFormulaTree(10086L);
        // 获取单个树下所有节点
        List<FormulaTree> oneTreeAllNode = formulaTreeChain.getOneTreeAllNode(formulaTrees.get(0));
        System.out.println(formulaTrees);
    }

    @Test
    public void testStrategyFactory() {
        strategyFactory.getStrategy("addStrategy");
    }

    /**
     * 测试公式生成
     */
    @Test
    public void testGenerateFormula() {
        List<FormulaTree> formulaTrees = formulaTreeChain.buildStationFormulaTree(10086L);
        // 生成公式结果
        List<Formula> formulaList = new ArrayList<>(10);

        // 单棵树生成公式
        for (FormulaTree formulaTree : formulaTrees) {
            // 单棵树下所有的节点
            List<FormulaTree> oneTreeAllNode = formulaTreeChain.getOneTreeAllNode(formulaTree);
            // 单个节点生成公式
            for (FormulaTree tree : oneTreeAllNode) {
                // TODO 获取该节点下所有计算项 这里模拟两个
                List<String> meterNames = Arrays.asList("1_H_TotalEnergy_AccessGrid", "1_D_TotalEnergyFee_Discharged");

                for (String meterName : meterNames) {
                    // TODO 数据库中配置了对应meterName使用的算法策略 这里模拟
                    int index = new Random().nextInt(3);
                    String strategyName = STRATEGY_NAME_LIST.get(index);
                    AbstractFormulService strategy = strategyFactory.getStrategy(strategyName);
                    strategy.generalFormula(formulaList, tree, 10086L, meterName);
                }
            }
        }
    }
}
