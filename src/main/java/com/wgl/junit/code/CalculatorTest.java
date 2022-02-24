package com.wgl.junit.code;

public class CalculatorTest {
    public static void main(String[] args) {
        // 创建对象
        Calculator calculator = new Calculator();

        // 调用
        /*int result = calculator.add(1, 2);
        System.out.println(result);*/

        int result = calculator.sub(1, 1);
        System.out.println(result);
    }
}
