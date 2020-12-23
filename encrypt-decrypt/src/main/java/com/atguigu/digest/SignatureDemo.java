package com.atguigu.digest;

import com.atguigu.rsa.RSADemo;

import java.security.PrivateKey;
import java.security.PublicKey;

public class SignatureDemo {
    public static void main(String[] args) throws Exception{
        String input = "镜华";

        PublicKey publicKey = RSADemo.getpublicKey("a.pub", "RSA");
        PrivateKey privateKey = RSADemo.getprivateKey("b.pri", "RSA");


    }
}
