package com.niezhiliang.proxy;

/**
 * @Author NieZhiLiang
 * @Email nzlsgg@163.com、
 * 代理模式
 */
public class ProxyPatternDemo {

    public static void main(String[] args) {

        Image image = new ProxyImage("test_10mb.jpg");

        //图像将从磁盘加载
        image.display();
        System.out.println("");

        //图像将无法从磁盘加载
        image.display();
    }
}
