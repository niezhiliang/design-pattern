package com.niezhiliang.observer;

/**
 * @Author NieZhiLiang
 * @Email nzlsgg@163.com
 */
public class HexaObserver extends Observer {

    public HexaObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    public void update() {
        System.out.println( "Hex String: "
                + Integer.toHexString( subject.getState() ).toUpperCase());
    }
}
