package com.fly.modules.sys.oauth2;

import com.fly.common.exception.RRException;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * 生成TOKEN
 *
 * Created by xinshidai on 17/9/18.
 */
public class TokenGenerator {

    private static final char[] hexCode = "0123456789abcdef".toCharArray();

    public static String generateValue() {
        return generateValue(UUID.randomUUID().toString());
    }

    public static String toHexString(byte[] data) {
        if (data == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder(data.length * 2);
        for (byte b : data) {
            // 取出这个字节的高4位，然后与0x0f与运算，得到一个0-15之间的数据，通过HEX.charAt(0-15)即为16进制数
            builder.append(hexCode[(b >> 4) & 0xF]);
            // 取出这个字节的低位，与0x0f与运算，得到一个0-15之间的数据，通过HEX.charAt(0-15)即为16进制数
            builder.append(hexCode[(b & 0xF)]);
        }
        return builder.toString();
    }

    public static String generateValue(String param) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(param.getBytes());
            byte[] messageDigest = algorithm.digest();
            return toHexString(messageDigest);
        } catch (Exception e) {
            throw new RRException("生成Token失败", e);
        }

    }
}
