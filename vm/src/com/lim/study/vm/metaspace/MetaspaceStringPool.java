package com.lim.study.vm.metaspace;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lim
 * @Description:
 * @Date: created in 2019/4/21
 */
public class MetaspaceStringPool {
    static String  base = "string";


    //-Xms20m -Xmx20m -XX:PermSize=8m -XX:MaxPermSize=8m
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        for (int i=0;i< Integer.MAX_VALUE;i++){
            String str = base + base;
            base = str;
            list.add(str.intern());
        }
    }
}
