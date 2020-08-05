package src.com.liuliuliu.kaiser;

import org.junit.Test;

/**
 * @author Liuty
 * 恺撒加密
 */
public class KaiserDemo {

    @Test
    public void testKaiser(){

        //原文
        String input = "helloword";

        //密钥为 往右移动3位
        int key = 3;
        StringBuilder result = new StringBuilder();
        char[] chars = input.toCharArray();
        for (char achar : chars) {
            int c = achar;
            //移动
            c = c+key;
            System.out.println((char)c);
            result.append((char) c);
        }

        System.out.println(result.toString());
    }

    @Test
    public void testDecryptAndEncrypt(){

        /**
         * 测试加密和解密
         */

        String orignal = "Hello world";
        // 往右边偏移三位
        int key = 6;
        // 选中我即将抽取的代码，按快捷键Ctrl + Alt + M
        String encryptKaiser =  encryptKaiser(orignal,key);
        System.out.println("加密：" + encryptKaiser);
        String decryptKaiser =  decryptKaiser(encryptKaiser,key);
        System.out.println("解密：" + decryptKaiser);
    }

    /**
     * 使用凯撒加密方式加密数据
     *
     * @param orignal :原文
     * @param key     :密钥
     * @return :加密后的数据
     */
    public static String encryptKaiser(String orignal, int key) {
        // 将字符串转为字符数组
        char[] chars = orignal.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            // 获取字符的ascii编码
            int asciiCode = aChar;
            // 偏移数据
            asciiCode += key%26;
            // 将偏移后的数据转为字符
            char result = (char) asciiCode;
            // 拼接数据
            sb.append(result);
        }
        return sb.toString();
    }

    /**
     * 使用凯撒加密方式解密数据
     *
     * @param encryptedData :密文
     * @param key           :密钥
     * @return : 源数据
     */
    public static String decryptKaiser(String encryptedData, int key) {
        // 将字符串转为字符数组
        char[] chars = encryptedData.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            // 获取字符的ASCII编码
            int asciiCode = aChar;
            // 偏移数据
            asciiCode -= key%26;
            // 将偏移后的数据转为字符
            char result = (char) asciiCode;
            // 拼接数据
            sb.append(result);
        }
        return sb.toString();
    }

}
