package com.atguigu.desaes;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * 对称加密
 */
public class DesAesDemo {
    public static void main(String[] args) throws Exception{
        //原文
        String input = "硅谷";
        //秘钥
        String key = "12345678";
        //表示算法
        String transformation = "DES";
        //创建加密对象
        Cipher cipher = Cipher.getInstance(transformation);
        String encryption = "DES";
        //参数一:表示key的字节      参数二:表示加密的类型
        SecretKeySpec sertificate = new SecretKeySpec(key.getBytes(), encryption);
        //init进行参数初始化 参数一:加密模式和解密模式 参数二:加密规则
        cipher.init(Cipher.ENCRYPT_MODE,sertificate);
        //调用加密和解密的方法    dofinal 这里表示密文，因为是ENCRYPT_MODE模式
        //参数表示原文的字节数组
        byte[] bytes = cipher.doFinal(input.getBytes());
        //创建Base64编码对象      apache下的
        String encode = Base64.encode(bytes);
        System.out.println(encode);

//        for (byte aByte : bytes) {
//            System.out.println(aByte);
//        }
//        //打印密文，直接打印密文会出现乱码
//        //出现乱码的原因是在编码表里找不到对应的字符
//        System.out.println(new String(bytes));

    }
}
