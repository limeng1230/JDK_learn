package com.lim.study.mbean;

/**
 * @Author: lim
 * @Description:
 * @Date: created in 2019/4/20
 */
public class Hello implements HelloMBean {


    private static final String name = "limeng";

    public int cacheSize = 2500;


    @Override
    public void sayHello() {
        System.out.println("sayHello is called");
    }

    @Override
    public int add(int x, int y) {
        System.out.println("add is called");
        return x+y;
    }

    @Override
    public String getName() {
        //在实际场景中，属性值可能会随着resource的运行而改变。
        return name;
    }

    @Override
    public int getCacheSize() {
        return cacheSize;
    }

    @Override
    public void setCacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
        System.out.println(cacheSize + " is set");
    }

}
