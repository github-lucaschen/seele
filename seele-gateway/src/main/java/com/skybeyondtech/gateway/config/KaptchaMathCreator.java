package com.skybeyondtech.gateway.config;

import com.google.code.kaptcha.text.impl.DefaultTextCreator;
import org.apache.commons.lang3.StringUtils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class KaptchaMathCreator extends DefaultTextCreator {
    private static final String[] C_NUMBERS = "0,1,2,3,4,5,6,7,8,9,10".split(",");


    @Override
    public String getText() {
        SecureRandom random;
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            return StringUtils.EMPTY;
        }
        int result = 0;
        int x = random.nextInt(10);
        int y = random.nextInt(10);
        StringBuilder suChinese = new StringBuilder();
        int randomOperands = random.nextInt(3);
        if (randomOperands == 0) {
            result = x * y;
            suChinese.append(C_NUMBERS[x]);
            suChinese.append("*");
            suChinese.append(C_NUMBERS[y]);
        } else if (randomOperands == 1) {
            if ((x != 0) && y % x == 0) {
                result = y / x;
                suChinese.append(C_NUMBERS[y]);
                suChinese.append("/");
                suChinese.append(C_NUMBERS[x]);
            } else {
                result = x + y;
                suChinese.append(C_NUMBERS[x]);
                suChinese.append("+");
                suChinese.append(C_NUMBERS[y]);
            }
        } else {
            if (x >= y) {
                result = x - y;
                suChinese.append(C_NUMBERS[x]);
                suChinese.append("-");
                suChinese.append(C_NUMBERS[y]);
            } else {
                result = y - x;
                suChinese.append(C_NUMBERS[y]);
                suChinese.append("-");
                suChinese.append(C_NUMBERS[x]);
            }
        }
        suChinese.append("=?@").append(result);
        return suChinese.toString();
    }
}
