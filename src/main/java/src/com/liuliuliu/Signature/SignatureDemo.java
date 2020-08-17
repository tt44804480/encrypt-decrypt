package src.com.liuliuliu.Signature;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 签名
 */
public class SignatureDemo {

    //下面的测试方法可以生成公钥-私钥
    public static String PUBLIC_lEY="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDBBO5gFWXOPWVif4cRRjZeC4T/1m9bXW/GdKQJLpvjFsRJ1HlmZQkxwAXM+hqwWHUj5rGtJs4pNHdcRGWvL7yvvCdmE4XNVVK8HgQTyp5szaTKMsvW0AoSKuFQji2/A/VUCPg389Wo8u2C3wVuYyeINLnyIg/NMu1vjQo4nFjlwIDAQAB";
    public static String PRIVATE_lEY="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMMEE7mAVZc49ZWJ/hxFGNl4LhP/Wb1tdb8Z0pAkum+MWxEnUeWZlCTHABcz6GrBYdSPmsa0mzik0d1xEZa8vvK+8J2YThc1VUrweBBPKnmzNpMoyy9bQChIq4VCOLb8D9VQI+Dfz1ajy7YLfBW5jJ4g0ufIiD80y7W+NCjicWOXAgMBAAECgYEAhzwhjYcAU7DNJRkfXW1RMSWVFosCKUlp2M7IZOsDWm2DjV6t/UFGKUbSukRlzPozpFq9Y0RFS7+IRT6uitByvN2kw3My7apB3XN90F+gC8p3mAQaVypE1LHZJwZ9NGdCK7HPmiFd6KT2Hh8sIaQ5/a5pOdqrXwgr1a3RluJzUZkCQQD4gn65a8ywqnVPbhWDjIZd8/gD9WVFtHiyC5X8mznS64GeHiwkXcgW7xVNiYgT1+m4En+xC7yS4b59L9M7/mfrAkEAyOTSqbIVz0/er0+0dFaC5CG8MauqL+KhZSsem4WT5IM7G5TyO5ZFsdCyZkwuE690kaFcrKO77XmRLQYtn1EUBQJANcXe7/Zgi1XHHpHee+GbbZcN8qVV7ZhYiYYyRT+9amNlX7Z9rLzSQAwp2uZImKqeuyLdmIzGmC3azzHbDl9FUwJBAIBsPvs6G9UeQsWhV0NztlLt+edqzO21m+UXjoZu+t7+wT9dlQhghQcp2ZGpq17sL8GU/LBkLxOdPiAhLfMFmp0CQCPjTD7FCW5YeRYMKdQDZSSwNYb0Ktry7b3/LZuofl/3oybmuA5IzZOn4GweHhvrsrmjCKHHCfJeF8dK0UUxK0I=";


    public static void main(String[] args) throws Exception {
        String a = "123";

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(PRIVATE_lEY)));
        PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(Base64.decode(PUBLIC_lEY)));

        String signaturedData = getSignature(a, "sha256withrsa", privateKey);
        System.out.println("签名的字符串： "+signaturedData);


        boolean b = verifySignature(a, "sha256withrsa", publicKey, signaturedData);

        System.out.println("验签结果："+b);

    }

    /**
     * 生成签名
     *
     * @param input      : 原文
     * @param algorithm  : 算法
     * @param privateKey : 私钥
     * @return : 签名
     * @throws Exception
     */
    private static String getSignature(String input, String algorithm, PrivateKey privateKey) throws Exception {
        // 获取签名对象
        Signature signature = Signature.getInstance(algorithm);
        // 初始化签名
        signature.initSign(privateKey);
        // 传入原文
        signature.update(input.getBytes());
        // 开始签名
        byte[] sign = signature.sign();
        // 对签名数据进行Base64编码
        return Base64.encode(sign);
    }

    /**
     * 校验签名
     *
     * @param input          : 原文
     * @param algorithm      : 算法
     * @param publicKey      : 公钥
     * @param signaturedData : 签名
     * @return : 数据是否被篡改
     * @throws Exception
     */
    private static boolean verifySignature(String input, String algorithm, PublicKey publicKey, String signaturedData) throws Exception {
        // 获取签名对象
        Signature signature = Signature.getInstance(algorithm);
        // 初始化签名
        signature.initVerify(publicKey);
        // 传入原文
        signature.update(input.getBytes());
        // 校验数据
        return signature.verify(Base64.decode(signaturedData));

    }
}
