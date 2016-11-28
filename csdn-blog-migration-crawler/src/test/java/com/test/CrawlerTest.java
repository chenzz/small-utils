package com.test;

import crawler.Crawler;
import org.junit.Test;

import java.io.IOException;

/**
 * @author: chenzhongzheng
 * @desciption:
 * @date: 2016-11-28
 */
public class CrawlerTest {
    @Test
    public void test1() throws IOException {
        String[] args = new String[] {"timberwolf_2012", "/Users/chenzhongzheng/Desktop/blog/"};
        Crawler.main(args);
    }
}
