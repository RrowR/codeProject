package com.atguigu.rsa;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.commons.io.FileUtils;

import javax.crypto.Cipher;
import java.io.File;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA:非对称加密
 */
public class RSADemo {
    public static void main(String[] args) throws Exception {
        String input = "美美";
        //选择加密算法
        String algorithm = "RSA";

        //生成密钥文件保存到本地文件中
        generateKeyToFile(algorithm, "a.pub", "b.pri");

        System.out.println("--------------------------这里是获得到的私钥对象------------------------------");
        //读取本地文件中的私钥
        PrivateKey privateKeyObject = getprivateKey("b.pri", algorithm);
        System.out.println(privateKeyObject);
        System.out.println("--------------------------这里是获得到的私钥对象------------------------------");
        System.out.println("--------------------------这里是获得到的公钥对象------------------------------");
        //读取本地文件中的公钥
        PublicKey publicKeyObject = getpublicKey("a.pub", algorithm);
        System.out.println(publicKeyObject);
        System.out.println("--------------------------这里是获得到的公钥对象------------------------------");


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
        /*System.out.println("私钥是:"+privateEncode);
        System.out.println();
        System.out.println("公钥是:"+publicEncode);*/


        //这里是非对称加密，只能是不同的密钥进行加密与解密
        encriptAnddecript(input, algorithm, privateKey, publicKey);

    }

    public static PublicKey getpublicKey(String pubpath, String algorithm) throws Exception {
        String publicKeyString = FileUtils.readFileToString(new File(pubpath), Charset.defaultCharset());
//        创建一个key工厂
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        //创建公钥规则，注意，这里与私钥是不一样的对象规则      X509EncodedKeySpec
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decode(publicKeyString));
        //返回公钥对象
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 读取私钥
     *
     * @param pripath   私钥路径
     * @param algorithm 算法
     * @return 不想返回String类型的对象而是返回私钥对象
     */
    public static PrivateKey getprivateKey(String pripath, String algorithm) throws Exception {
        String privateKeystring = FileUtils.readFileToString(new File(pripath), Charset.defaultCharset());
        //创建一个key工厂
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        //创建私钥key的规则
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decode(privateKeystring));
        //返回私钥对象
        return keyFactory.generatePrivate(keySpec);
    }


    /**
     * 保存公钥和私钥到根目录
     *
     * @param algorithm RSA加密算法
     * @param pubPath   公钥
     * @param priPath   私钥
     */
    private static void generateKeyToFile(String algorithm, String pubPath, String priPath) throws Exception {
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
        //将公钥和私钥保存到根目录
        FileUtils.writeStringToFile(new File(pubPath), publicEncode, Charset.forName("UTF-8"));
        FileUtils.writeStringToFile(new File(priPath), privateEncode, Charset.forName("UTF-8"));
    }

    private static void encriptAnddecript(String input, String algorithm, Key privateKey, Key publicKey) throws Exception {
        //创建加密对象,并传入加密算法，这里是RSA非对称加密
        Cipher cipher = Cipher.getInstance(algorithm);
        //传入加密规则，传入私钥加密
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] bytes = cipher.doFinal(input.getBytes());
        System.out.println(Base64.encode(bytes));

        //使用非对称加密只能一个密钥进行加密另一个密钥进行解密
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] bytes1 = cipher.doFinal(bytes);
        System.out.println(new String(bytes1));
    }


}
