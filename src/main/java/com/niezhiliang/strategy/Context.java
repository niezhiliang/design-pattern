package com.niezhiliang.strategy;

/**
 * @Author NieZhiLiang
 * @Email nzlsgg@163.com
 */
public class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int num, int num2) {
        return strategy.doOperation(num, num2);
    }
}
