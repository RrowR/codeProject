package com.atguigu.kaiser;

public class KaiserDemo {
    public static void main(String[] args) {
        //定义原文
        String input = "Hello World";
        //把原文把→移动三位
        int key = 3;
        //凯撒加密
        String s = encryptKaiser(input);
        System.out.println("加密:"+s);
        String descrypt = descryptKaiser(s,key);
        //明文
        System.out.println("明文:"+descrypt);
    }

    /**
     *
     * @param s 密文
     * @param key   秘钥
     * @return
     */
    private static String descryptKaiser(String s, int key) {
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            int b = aChar;
            b -= key;
            char newB = (char) b;
            sb.append(newB);
        }
        return sb.toString();
    }

    private static String encryptKaiser(String input) {
        //抽取快捷键 ctrl+alt+m
        //把字符串变成字节数据
        char[] chars = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            int b = aChar;
            //往右移动三位
            b = b + 3;
            char newb = (char) b;
            sb.append(newb);
        }
//        System.out.println("密文："+sb.toString());
        return sb.toString();
    }
}
