package com.lim.study.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: lim
 * @Description: -Xmx20m -Xms20m -XX:+PrintGC -XX:+PrintGCDetails 触发Gc
 * @Date: created in 2019/4/26
 */
public class ReachabilityFence {

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    private static List<ExecutorService> executorServiceList = new ArrayList<>();

    public static void main(String[] args) {
        for (int i = 0; i < 2000; i++) {
            newSingleThreadPool3();
        }
        System.out.println(atomicInteger.get());
    }
    //RejectedExecutionException
    public static void newSingleThreadPool(){
        Executors.newSingleThreadExecutor().submit(new Runnable() {
            @Override
            public void run() {
                byte[] bytes = new byte[1024 * 1024 * 4];
                System.out.println(Thread.currentThread().getName());
            }
        });
    }

    //RejectedExecutionException
    public static void newSingleThreadPool2(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                byte[] bytes = new byte[1024 * 1024 * 4];
                atomicInteger.getAndIncrement();
                System.out.println(Thread.currentThread().getName());
            }
        });
    }

    public static void newSingleThreadPool3(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                byte[] bytes = new byte[1024 * 1024 * 4];
                atomicInteger.getAndIncrement();
                System.out.println(Thread.currentThread().getName());
            }
        });
        executorServiceList.add(executorService);
    }

    //不抛出异常，但因为shutdown并不等待之前提交的任务执行完，因此可能会出现有些任务没有执行
    public static void newSingleThreadPool4(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                byte[] bytes = new byte[1024 * 1024 * 4];
                atomicInteger.getAndIncrement();
                System.out.println(Thread.currentThread().getName());
            }
        });
        executorService.shutdown();
    }
}
