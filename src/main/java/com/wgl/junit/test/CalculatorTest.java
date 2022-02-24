package com.wgl.junit.test;

import com.wgl.junit.code.Calculator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {

    /**
     * 初始化方法
     * 用于资源申请，所有测试方法在执行之前都会先执行该方法
     */
    @Before
    public void init() {
        System.out.println("init...");
    }

    /**
     * 释放资源方法：
     * 在所有测试方法执行完后，都会自动执行该方法
     */
    @After
    public void close() {
        System.out.println("close...");
    }

    /**
     * CES
     */
    @Test
    public void testAdd() {
//        System.out.println("我被执行了");

        // 1.创建计数器对象
        System.out.println("testAdd...");
        Calculator calculator = new Calculator();

        // 2.调用add方法
        int result = calculator.add(1, 2);

        // 3.断言 断言结果是3
        Assert.assertEquals(3, result);
    }

    @Test
    public void testSub(){
        // 1.创建计数器对象
        Calculator calculator=new Calculator();

        // 2.调用sub方法
        int result =calculator.sub(1,2);
        System.out.println("testSub");

        // 3.创建计数器对象
        Assert.assertEquals(-1,result);
    }
}
