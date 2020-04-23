package com.monkey.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Security {
    public static String encryptPwd(String plain) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            byte[][] leftAndRight = split(plain.getBytes());
            byte[] left = leftAndRight[0];
            byte[] right = leftAndRight[1];
            for (int i = 0; i < 10; ++i) {
                left = md5.digest(right);
                right = md5.digest(left);
            }
            return new BigInteger(alter(right, left)).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return plain;
    }

    private static byte[][] split(byte[] bytes) {
        byte[] left = new byte[bytes.length / 2];
        byte[] right = new byte[bytes.length - bytes.length / 2];
        int p = 0, q = 0;
        for (int j = 0; j < bytes.length; ++j) {
            if (j < bytes.length / 2) left[p++] = bytes[j];
            else right[q++] = bytes[j];
        }
        return new byte[][] { left, right };
    }

    private static byte[] alter(byte[] left, byte[] right) {
        byte[] result = new byte[left.length + right.length];
        int p = 0;
        for (int i = 0; i < left.length; ++i) {
            result[p++] = left[i];
            result[p++] = right[i];
        }
        return result;
    }
}
