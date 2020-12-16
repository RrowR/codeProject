package com.atguigu.ascii;

public class AsciiDemo {
    public static void main(String[] args) {
//        char a = 'a';
//        int b = a;
        //定义字符串
        String a = "AaZ";
        //需要拆开字符串
        char[] chars = a.toCharArray();
        for (char aChar : chars) {
            int ascii = aChar;
            System.out.println(ascii);
        }
//        System.out.println(a);
    }
}
