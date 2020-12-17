package com.atguigu.bytebit;

public class BiteBitDemo {
    /**
     *  如果是UTF-8    一个中文是3个字节
     *  如果是GBK      一个中文是2个字节
     *  如果是英文，不会出现编码格式问题
     */
    public static void main(String[] args) throws Exception{
        String name = "A";
        byte[] bytes = name.getBytes("GBK");
        for (byte aByte : bytes) {
            System.out.println(aByte);
            String s = Integer.toBinaryString(aByte);
            System.out.println(s);
        }
    }
}
