package com.company.test;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.DigestUtils;

import java.math.BigDecimal;

/**
 * @author bin.li
 * @date 2020/9/15
 */
public class Test {

    public static void main(String[] args) {
//        Test test = new Test();
//        String ss = "a";
//        String i = test.test11(ss);
//        System.out.println(ss);
//        System.out.println(i);
//
//        System.out.println(Integer.toBinaryString(-1231231231));
//        System.out.println(Integer.toHexString(123));
//        System.out.println(Integer.toOctalString(123));
//        System.out.println(Integer.highestOneBit(123));
//        System.out.println(Integer.lowestOneBit(123));
//
//        System.out.println(false || true);

        BigDecimal bigDecimal = new BigDecimal("71");
        BigDecimal bigDecimal2 = new BigDecimal("622");
        System.out.println(bigDecimal.compareTo(bigDecimal2) > 0);

        String pwd = "123456";
        String salt = RandomStringUtils.randomAlphanumeric(20);
        System.out.println(salt);
        String password = salt + pwd + salt;
        System.out.println(DigestUtils.md5DigestAsHex(password.getBytes()));

    }

    public String test11(String ss) {
        String a = "0";
        try {
            throw new Exception("1");
        } catch (Exception e) {
            a = "1";
            ss = "b";
            return ss;
        } finally {
            a = "2";
            ss = "c";
        }
    }
}
