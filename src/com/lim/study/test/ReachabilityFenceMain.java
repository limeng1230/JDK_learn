package com.lim.study.test;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: lim
 * @Description: -XX:-BackgroundCompilation -XX:CompileCommand=dontinline,*,*
 * @Date: created in 2019/4/26
 */
public class ReachabilityFenceMain {

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args){
        for(int i=0; i < 2000; i++){
            try{
                new Person(atomicInteger.getAndIncrement()).beSmart(20);
                //下面这种写法，person不会被gc掉
//                Person person = new Person();
//                person.beSmart(20);
            }catch (NullPointerException e){
                e.printStackTrace();
                break;
            }
        }
    }
}
class Person{

    private int id;
    private Head head;
    Person(int i){
        this.id = i;
        this.head = new Head(i);
    }

    public void beSmart(int iq){
        this.head.beSmart(iq);
    }

    @Override
    public void finalize(){
        System.out.println(id + " Person.finalize()....");
        this.head.clearHeadCell();
    }
}

class Head{

    private int id;
    HeadCell headCell;

    Head(int i){
        this.id = i;
        this.headCell = new HeadCell();
    }
    public int beSmart(int iq){
        System.gc();
        System.runFinalization();
        return this.headCell.beSmart(iq);
    }

    public void clearHeadCell(){
        this.headCell = null;
    }

    @Override
    public void finalize(){
        System.out.println(id + " Head.finalize()....");
    }
}
class HeadCell{
    int curIq;
    public int beSmart(int iq){
        this.curIq += iq;
        return this.curIq;
    }

}


