package com.company.test;

/**
 * @author bin.li
 * @date 2020/9/15
 */
public class Test {

    public static void main(String[] args) {
        Test test = new Test();
        String ss = "a";
        String i = test.test11(ss);
        System.out.println(ss);
        System.out.println(i);

        System.out.println(Integer.toBinaryString(-1231231231));
        System.out.println(Integer.toHexString(123));
        System.out.println(Integer.toOctalString(123));
        System.out.println(Integer.highestOneBit(123));
        System.out.println(Integer.lowestOneBit(123));
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
