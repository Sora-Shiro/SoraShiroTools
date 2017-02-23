package com.sorashiro.sorashirotools;

/**
 * Created by GameKing on 2016/5/27.
 */
public class ByteTool {

    /**
     * 转换short为byte
     *
     * @param b byte[]
     * @param s 需要转换的short
     * @param index 第几位开始取
     * @return short
     */
    public static void putShort(byte b[], short s, int index) {
        b[index + 1] = (byte) (s >> 8);
        b[index + 0] = (byte) (s >> 0);
    }

    /**
     * 通过byte数组取到short
     *
     * @param b byte[]
     * @param index  第几位开始取
     * @return short
     */
    public static short getShort(byte[] b, int index) {
        return (short) (((b[index + 1] << 8) | b[index + 0] & 0xff));
    }

    /**
     * 将data字节型数据转换为0~255 (0xFF 即BYTE)
     * @param data byte
     * @return int
     */
    public static int getUnsignedByte (byte data){
        return data&0x0FF ;
    }

    /**
     * 将data字节型数据转换为0~65535 (0xFFFF 即 WORD)
     * @param data short
     * @return int
     */
    public static int getUnsignedByte (short data){
        return data&0x0FFFF;
    }

    /**
     * 将int数据转换为0~4294967295 (0xFFFFFFFF即DWORD)
     * @param data int
     * @return long
     */
    public static long getUnsignedIntt (int data){
        return data&0x0FFFFFFFF ;
    }


}
