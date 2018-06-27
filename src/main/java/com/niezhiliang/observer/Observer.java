package com.niezhiliang.observer;

/**
 * @Author NieZhiLiang
 * @Email nzlsgg@163.com
 */
public  abstract class Observer {

    protected  Subject subject;

    public abstract void update();
}
