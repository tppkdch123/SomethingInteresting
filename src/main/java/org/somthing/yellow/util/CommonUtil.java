package org.somthing.yellow.util;

import java.util.Random;

public class CommonUtil {
    public static Random random = new Random();

    public static String generateRandomVerificationCode() {
        char[] chars = new char[4];
        for (int i = 0; i < 4; i++) {
            chars[i] = (char) (random.nextInt(25) + 65);
        }
        return new String(chars);
    }

    public static String wireVerificationCode(String str,String code){
      return str.replaceFirst("\\{}",code);
    }
}
