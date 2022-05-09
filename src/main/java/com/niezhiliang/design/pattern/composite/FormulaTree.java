package com.niezhiliang.design.pattern.composite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author niezhiliang
 * @version v
 * @date 2022/5/7 13:21
 * 设备节点实体类 组合模式，具体组合在责任链模式中实现
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class FormulaTree implements Serializable {

    /**
     * 层级ID/设备ID
     */
    private Long id;

    /**
     * 层级名称/设备名称（无实际意义、测试为了判错）
     */
    private String name;

    /**
     *  节点类型
     *  1,"国网计量点",2,"光伏并网点",3,"储能并网点",4,"风电计量点"
     *  ,5,"水计量点",6,"站点",7,"资产目录",8, "设备"
     */
    private Integer type;

    /**
     * 分类分项计算类型（设备类型该值为空）
     * 1.聚合 2. 统计
     */
    private Integer levelCalcType;

    /**
     * 父节点
     */
    private FormulaTree parent;

    /**
     * 子节点
     */
    private final List<FormulaTree> childTreeList = new ArrayList<>(10);



    /**
     * 添加子节点
     */
    public void addChildren(FormulaTree tree) {
        // 设置父节点
        tree.setParent(this);
        childTreeList.add(tree);
    }

    /**
     * 批量添加子节点
     * @param treeList
     */
    public void addChildren(Collection<FormulaTree> treeList) {
        treeList.forEach((e) -> addChildren(e));
    }

    /**
     * 删除子节点，暂时不对外开放
     * @param tree
     * @return
     */
    private boolean removeChildren(FormulaTree tree) {
        return childTreeList.remove(tree);
    }

    /**
     * 获取子节点
     */
    public List<FormulaTree> getChildren() {

        return this.childTreeList;
    }

    public FormulaTree(Long id, String name, Integer type, Integer levelCalcType) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.levelCalcType = levelCalcType;
    }

    @Override
    public String toString() {
        return "FormulaTree{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", parent=" + parent +
                '}';
    }
}
