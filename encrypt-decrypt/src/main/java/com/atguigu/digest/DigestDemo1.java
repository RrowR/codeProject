package com.atguigu.digest;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestDemo1 {
    public static void main(String[] args) throws Exception {
        //原文
        String input = "aa";
        //使用程序中的md5 ：   4124bca9335c27f86f24ba207a4912          只要在a和8前面进行补0就可以达到下面的结果
        //使用网站中的md5 ：   4124bc0a9335c27f086f24ba207a4912
//                            4124bc0a9335c27f086f24ba207a4912
        //算法
        String algorithm = "MD5";
        //消息摘要算法，并传入算法格式
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        //执行消息摘要算法
        byte[] digest1 = digest.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();
        //使用base64进行转码并打印
//        System.out.println(Base64.encode(digest1));
        //对密文进行迭代
        for (byte b : digest1) {
            //把密文转换成16进制
            //0x代表16进制  ff代表    1111 1111 一个字节8位
            String s = Integer.toHexString(b & 0xff);
            //判断长度是否是1，如果是就补0
            if(s.length()==1){
                s = "0" + s ;
            }
            sb.append(s);
//            System.out.print(s);
        }
        //打印对象使用toString就可以了
        System.out.print(sb.toString());
    }
}
