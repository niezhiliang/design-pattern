package com.niezhiliang.strategy;

/**
 * @Author NieZhiLiang
 * @Email nzlsgg@163.com
 */
public class OperationSubstract implements  Strategy {

    public int doOperation(int num, int num2) {

        return num - num2;
    }
}
