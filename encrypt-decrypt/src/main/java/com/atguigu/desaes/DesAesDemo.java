package com.atguigu.desaes;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 对称加密
 */
public class DesAesDemo {
    public static void main(String[] args) throws Exception{
        //原文
        String input = "美美";
        //秘钥key
        //如果使用des进行加密   秘钥必须是8个字节
        String key = "12345678";
        //表示算法
        String transformation = "DES";
        //加密类型
        String encryptionType = "DES";
        String decryptionType = "DES";

        String encryptDES = encryptDES(input, key, transformation, encryptionType);
        System.out.println("加密:"+encryptDES);

        String decryptDES = decryptDES(encryptDES, key, transformation, decryptionType);
        System.out.println("解密:"+decryptDES);
    }

    /**
     *
     * @param encryptDES     密文
     * @param key       秘钥
     * @param transformation    加密/解密算法
     * @param decryptionType    解密类型
     * @return
     * @throws Exception
     */
    private static String decryptDES(String encryptDES, String key, String transformation, String decryptionType) throws Exception{
        //创建加密实例    传入加密类型=>DES类型
        Cipher cipher = Cipher.getInstance(transformation);
        //创建加密规则==>>需要传入秘钥的字节数组和加密类型
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), decryptionType);
        //在执行解密之前需要初始化加密实例==>>传入两个参数  方法类型 和 加密规则（上面就有）
        cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);
        //执行解密方法    需要传入密文的字节数组
        byte[] bytes = cipher.doFinal(Base64.decode(encryptDES));
        //讲解密的信息传承String类型进行反悔
        return new String(bytes);
    }

    /**
     *
     * @param input     原文
     * @param key       秘钥
     * @param transformation    加密/解密算法
     * @param encryptionType    加密类型
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    private static String encryptDES(String input, String key, String transformation, String encryptionType) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        //创建加密对象
        Cipher cipher = Cipher.getInstance(transformation);
        //参数一:表示key的字节      参数二:表示加密的类型
        SecretKeySpec sertificaterRule = new SecretKeySpec(key.getBytes(), encryptionType);
        //init进行参数初始化 参数一:加密模式和解密模式 参数二:加密规则
        cipher.init(Cipher.ENCRYPT_MODE,sertificaterRule);
        //调用加密和解密的方法    dofinal 这里表示密文，因为是ENCRYPT_MODE模式
        //参数表示原文的字节数组
        byte[] bytes = cipher.doFinal(input.getBytes());
        //创建Base64编码对象      apache下的
        String encode = Base64.encode(bytes);
        return encode;
//        for (byte aByte : bytes) {
//            System.out.println(aByte);
//        }
//        //打印密文，直接打印密文会出现乱码
//        //出现乱码的原因是在编码表里找不到对应的字符
//        System.out.println(new String(bytes));
    }
}
