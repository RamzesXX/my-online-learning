package com.khanchych.udemy.javaindepth.jvm;

public class ClassInitDemo extends Object {
    static  {
        System.out.println("ClassInitDemo::static init block");

    }
    {
        System.out.println("ClassInitDemo::init block");
    }
    public static void staticMethodA() {
        System.out.println("ClassInitDemo::staticMethodA");

    }
    public void methodB() {
        System.out.println("ClassInitDemo::methodB");
    }
    public static void main(String[] args) {
        ClassInitDemo classInitDemo = new ClassInitDemo();

        System.out.println("ClassInitDemo::main");
    }
}
