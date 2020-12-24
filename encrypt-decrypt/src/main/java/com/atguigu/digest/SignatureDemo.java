package com.atguigu.digest;

import com.atguigu.rsa.RSADemo;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class SignatureDemo {
    public static void main(String[] args) throws Exception{
        String input = "镜华";

        PublicKey publicKey = RSADemo.getpublicKey("a.pub", "RSA");
        PrivateKey privateKey = RSADemo.getprivateKey("b.pri", "RSA");

        String signaturedData = getSignature(input, "sha256withrsa", privateKey);
        System.out.println(signaturedData);
        //校验签名
        boolean b = varifySignature(input,"sha256withrsa",publicKey,signaturedData);
        System.out.println(b);
    }

    /**
     * 校验签名
     * @param input     原文
     * @param algorithm     算法
     * @param publicKey     公钥
     * @param signaturedData        数字签名
     * @return
     */
    private static boolean varifySignature(String input, String algorithm, PublicKey publicKey, String signaturedData) throws Exception{
        //获取签名对象
        Signature signature = Signature.getInstance(algorithm);
        //初始化校验
        signature.initVerify(publicKey);
        //传入原文
        signature.update(input.getBytes());
        //将数字签名与原文进行校验
        return signature.verify(Base64.decode(signaturedData));
    }

    /**
     * 生成数字签名
     * @param input     原文
     * @param algorithm     加密算法
     * @param privateKey        私钥
     * @return
     */
    private static String getSignature(String input, String algorithm, PrivateKey privateKey) throws Exception {
        //获得数字签名实例
        Signature signature = Signature.getInstance(algorithm);
        //初始化私钥
        signature.initSign(privateKey);
        //传入原文
        signature.update(input.getBytes());
        //开始签名
        byte[] sign = signature.sign();
        //将字节数组使用Base64进行编码并返回
        return Base64.encode(sign);
    }
}
