package src.com.liuliuliu.rsa;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.junit.Test;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA 加密解密
 */
public class RSAdemo {

    //下面的测试方法可以生成公钥-私钥
    public String PUBLIC_lEY="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDBBO5gFWXOPWVif4cRRjZeC4T/1m9bXW/GdKQJLpvjFsRJ1HlmZQkxwAXM+hqwWHUj5rGtJs4pNHdcRGWvL7yvvCdmE4XNVVK8HgQTyp5szaTKMsvW0AoSKuFQji2/A/VUCPg389Wo8u2C3wVuYyeINLnyIg/NMu1vjQo4nFjlwIDAQAB";
    public String PRIVATE_lEY="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMMEE7mAVZc49ZWJ/hxFGNl4LhP/Wb1tdb8Z0pAkum+MWxEnUeWZlCTHABcz6GrBYdSPmsa0mzik0d1xEZa8vvK+8J2YThc1VUrweBBPKnmzNpMoyy9bQChIq4VCOLb8D9VQI+Dfz1ajy7YLfBW5jJ4g0ufIiD80y7W+NCjicWOXAgMBAAECgYEAhzwhjYcAU7DNJRkfXW1RMSWVFosCKUlp2M7IZOsDWm2DjV6t/UFGKUbSukRlzPozpFq9Y0RFS7+IRT6uitByvN2kw3My7apB3XN90F+gC8p3mAQaVypE1LHZJwZ9NGdCK7HPmiFd6KT2Hh8sIaQ5/a5pOdqrXwgr1a3RluJzUZkCQQD4gn65a8ywqnVPbhWDjIZd8/gD9WVFtHiyC5X8mznS64GeHiwkXcgW7xVNiYgT1+m4En+xC7yS4b59L9M7/mfrAkEAyOTSqbIVz0/er0+0dFaC5CG8MauqL+KhZSsem4WT5IM7G5TyO5ZFsdCyZkwuE690kaFcrKO77XmRLQYtn1EUBQJANcXe7/Zgi1XHHpHee+GbbZcN8qVV7ZhYiYYyRT+9amNlX7Z9rLzSQAwp2uZImKqeuyLdmIzGmC3azzHbDl9FUwJBAIBsPvs6G9UeQsWhV0NztlLt+edqzO21m+UXjoZu+t7+wT9dlQhghQcp2ZGpq17sL8GU/LBkLxOdPiAhLfMFmp0CQCPjTD7FCW5YeRYMKdQDZSSwNYb0Ktry7b3/LZuofl/3oybmuA5IzZOn4GweHhvrsrmjCKHHCfJeF8dK0UUxK0I=";

    /**
     * 获取公钥-私钥
     */
    @Test
    public void testGetKey() throws Exception{
        // 加密算法
        String algorithm = "RSA";
        //  创建密钥对生成器对象
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        // 生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // 生成私钥
        PrivateKey privateKey = keyPair.getPrivate();
        // 生成公钥
        PublicKey publicKey = keyPair.getPublic();
        // 获取私钥字节数组
        byte[] privateKeyEncoded = privateKey.getEncoded();
        // 获取公钥字节数组
        byte[] publicKeyEncoded = publicKey.getEncoded();
        // 对公私钥进行base64编码
        String privateKeyString = Base64.encode(privateKeyEncoded);
        String publicKeyString = Base64.encode(publicKeyEncoded);
        // 打印私钥
        System.out.println(privateKeyString);
        // 打印公钥
        System.out.println(publicKeyString);
    }

    /**
     * 加密解密（私钥加密-公钥解密）
     */
    @Test
    public void testEncodedAndDecode() throws Exception{
        String algorithm = "RSA";
        String input = "哈哈";

        // 创建加密对象
        // 参数表示加密算法
        Cipher cipher = Cipher.getInstance(algorithm);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(this.PRIVATE_lEY)));
        PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(Base64.decode(this.PUBLIC_lEY)));
        // 初始化加密
        // 第一个参数:加密的模式
        // 第二个参数：使用私钥进行加密
        cipher.init(Cipher.ENCRYPT_MODE,privateKey);
        // 私钥加密
        byte[] bytes = cipher.doFinal(input.getBytes());
        System.out.println(Base64.encode(bytes));
        // 私钥进行解密
        cipher.init(Cipher.DECRYPT_MODE,publicKey);
        // 对密文进行解密，不需要使用base64，因为原文不会乱码
        byte[] bytes1 = cipher.doFinal(bytes);
        System.out.println(new String(bytes1));
    }
}
