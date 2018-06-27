package com.niezhiliang.proxy;

/**
 * @Author NieZhiLiang
 * @Email nzlsgg@163.com
 */
public class ProxyImage implements Image {

    private RealImage realImage;

    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    public void display() {

        if (realImage == null) {
            realImage = new RealImage(fileName);
        }

        realImage.display();
    }

}
