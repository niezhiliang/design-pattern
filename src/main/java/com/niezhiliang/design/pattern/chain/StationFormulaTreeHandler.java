package com.niezhiliang.design.pattern.chain;

import com.niezhiliang.design.pattern.composite.FormulaTree;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author niezhiliang
 * @version v
 * @date 2022/5/7 11:27
 * 站点
 */
@Order(1)
@Component
public class StationFormulaTreeHandler extends AbstractFormulaTreeHandler {

    @Override
    protected List<FormulaTree> compositeFormulaTree(Long stationId) {
        // 基础设备
        FormulaTree equipment1 = new FormulaTree(770001L,"国网_1", 8,1);
        FormulaTree equipment2 = new FormulaTree(770002L, "光伏_1",8,1);
        FormulaTree equipment3 = new FormulaTree(770003L, "光伏_2",8,1);
        FormulaTree equipment4 = new FormulaTree(770004L, "储能_1",8,1);
        FormulaTree equipment5 = new FormulaTree(770005L, "储能_2",8,1);
        FormulaTree equipment6 = new FormulaTree(770006L, "风电_1",8,1);
        FormulaTree equipment7 = new FormulaTree(770006L, "水_1",8,1);


        // 1,"国网计量点",2,"光伏并网点",3,"储能并网点",4,"风电计量点",5,"水计量点"
        FormulaTree water = new FormulaTree(220004L, "水计量点",5,1);
        water.addChildren(equipment7);

        FormulaTree wind = new FormulaTree(220004L, "风电并网点",4,1);
        wind.addChildren(equipment6);

        FormulaTree store = new FormulaTree(220004L, "储能并网点",3,1);
        store.addChildren(Arrays.asList(equipment4,equipment5));


        FormulaTree pv = new FormulaTree(220004L, "光伏并网点",2,1);
        pv.addChildren(Arrays.asList(equipment2,equipment3));


        FormulaTree guowang = new FormulaTree(220004L, "国网计量点",1,1);
        guowang.addChildren(Arrays.asList(equipment1,wind,store,pv));


        FormulaTree station = new FormulaTree(stationId, "测试站点",6,1);
        station.addChildren(Arrays.asList(guowang,water));

        return Arrays.asList(station);
    }
}
