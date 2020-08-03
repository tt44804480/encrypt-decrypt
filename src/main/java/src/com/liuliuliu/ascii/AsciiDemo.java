package src.com.liuliuliu.ascii;

import org.junit.Test;

/**
 * @Author: lty
 */
public class AsciiDemo {

    @Test
    public void testChar(){
        char a = 'A';
        int b = a;
        // 打印b 在ascii码中对应的十进制数是多少。
        System.out.println(b);
    }

    @Test
    public void testString(){
        String a = "AaZ";
        // 获取ascii码，需要把字符串转成字符
        char[] chars = a.toCharArray();
        for (char c : chars) {
            int asciiCode = c;
            System.out.println(asciiCode);
        }
    }
}
