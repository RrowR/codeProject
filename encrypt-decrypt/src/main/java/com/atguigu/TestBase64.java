package com.atguigu;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class TestBase64 {
    public static void main(String[] args) {
        //一个英文字母是1个字节，Base64需要3个字节，缺少1个字节用=补齐
        System.out.println(Base64.encode("1".getBytes()));
        System.out.println(Base64.encode("12".getBytes()));
        System.out.println(Base64.encode("132".getBytes()));
        //在UTF-8的编码格式下，一个中文是3个字节，3*2*8=48  正好是6个字节，不需要用=去补齐
        //所以在UTF-8编码下，中文的字节与Base64需要的字节一致
        System.out.println(Base64.encode("小明房".getBytes()));
    }
}
