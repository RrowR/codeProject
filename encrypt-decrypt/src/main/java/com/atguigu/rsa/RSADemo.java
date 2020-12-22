package com.atguigu.rsa;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * 非对称加密
 */
public class RSADemo {
    public static void main(String[] args) throws Exception {
        //选择加密算法
        String algorithm = "RSA";
//        KeyPairGenerator创建密钥对对象
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
//        生成密钥对实例
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
//        生成公私钥
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        //生成公私钥的字节数组，目的是为了使用base64进行编码，防止编码有奇怪的数字
        byte[] privateKeyEncoded = privateKey.getEncoded();
        byte[] publicKeyEncoded = publicKey.getEncoded();
        //使用Base64进行转码
        String privateEncode = Base64.encode(privateKeyEncoded);
        String publicEncode = Base64.encode(publicKeyEncoded);
        System.out.println("私钥是:"+privateEncode);
        System.out.println();
        System.out.println("公钥是:"+publicEncode);
    }
}
