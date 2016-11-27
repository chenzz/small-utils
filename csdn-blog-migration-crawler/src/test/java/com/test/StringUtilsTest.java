package com.test;

import org.junit.Test;
import util.StringUtils;

/**
 * @author: chenzhongzheng
 * @desciption:
 * @date: 2016-11-27
 */
public class StringUtilsTest {
    @Test
    public void test1() {
        System.out.println(StringUtils.eliminateSpecialStr4Yaml("\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\"));
        System.out.println(StringUtils.eliminateSpecialStr4Yaml("JavaScript中 null\\undefined\\'' 小结"));
        System.out.println(StringUtils.eliminateSpecialStr4Yaml("Leetcode: Decode Ways"));

    }
}
