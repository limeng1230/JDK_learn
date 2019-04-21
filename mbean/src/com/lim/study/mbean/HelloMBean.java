package com.lim.study.mbean;

/**
 * @Author: lim
 * @Description:
 * @Date: created in 2019/4/20
 */
public interface HelloMBean {

    public void sayHello();
    public int add(int x, int y);

    public String getName();

    public int getCacheSize();
    public void setCacheSize(int size);
}
