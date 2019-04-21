package com.lim.study.mbean;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * @Author: lim
 * @Description:
 * @Date: created in 2019/4/20
 */
public class Main {


    //这里的main是不是可以代表我们的程序，我们定义了mbean， 程序运行，将mbean注册到mbeanServer中。
    //如此就可以通过JMX来管理程序中的定义
    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, InterruptedException {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("com.lim.study.mbean:type=Hello");
        Hello mbean = new Hello();
        //将自己注册到mbeanServer中，通过传递自己的名字和本身。 类似thrift???
        mbs.registerMBean(mbean, name);


        System.out.println("Waiting forever...");
        Thread.sleep(Long.MAX_VALUE);
    }
}
