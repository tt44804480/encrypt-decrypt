package src.com.liuliuliu.bytebit;

import org.junit.Test;

/**
 * @author Liuty
 * 字节
 */
public class ByteBit {

    /**
     * 英文对应的字节数
     */
    @Test
    public void byteBitTest(){
        String a = "a";
        byte[] bytes = a.getBytes();
        for (byte b : bytes) {
            // 打印发现byte实际上就是ascii码
            System.out.println(b);
        }
    }

    /**
     * 中文对应的字节
     *
     * 中文在UTF-8编码下, 占据3个字节
     * 中文在GBK编码下, 占据2个字节
     * 英文无所谓编码格式
     */
    @Test
    public void byteBitChineseTest(){
        String a = "哈";
        byte[] bytes = a.getBytes();
        for (byte aByte : bytes) {
            System.out.println(aByte);
        }
    }
}
