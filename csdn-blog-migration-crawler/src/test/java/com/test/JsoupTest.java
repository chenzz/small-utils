package com.test;


import com.overzealous.remark.Remark;
import constants.CommonConstants;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author: chenzhongzheng
 * @desciption:
 * @date: 2016-11-26
 */
public class JsoupTest {

    /**
     * 测试Jsoup的使用
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {
        Document doc = Jsoup
                .connect("http://blog.csdn.net/timberwolf_2012/")
                .userAgent(CommonConstants.USER_AGENT)
                .get();
        Elements elements = doc.select("a:containsOwn(尾页)");
    }

    /**
     * 测试获取某一篇MarkDown格式的文章的内容并存储在硬盘上
     * @throws IOException
     */
    @Test
    public void test2() throws IOException {
        Document doc = Jsoup
                .connect("http://blog.csdn.net/timberwolf_2012/article/details/47350215")
                .userAgent(CommonConstants.USER_AGENT)
                .get();
        Elements elements = doc.select("div.markdown_views");
        String articleContent = elements.html();

        File file = new File("/Users/chenzhongzheng/Desktop/article_test.txt");

        try (
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        ) {
            bufferedWriter.write(articleContent);
        }
    }

    /**
     * 测试获取某一篇非MarkDown格式的文章的内容并存储在硬盘上
     * @throws IOException
     */
    @Test
    public void test3() throws IOException {
        Document doc = Jsoup
                .connect("http://blog.csdn.net/timberwolf_2012/article/details/8620978")
                .userAgent(CommonConstants.USER_AGENT)
                .get();
        Elements elements = doc.select("div.article_content");
        String articleContent = elements.html();

        File file = new File("/Users/chenzhongzheng/Desktop/article_test2.txt");

        try (
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        ) {
            bufferedWriter.write(articleContent);
        }
    }

    /**
     * 把html转换成markdown
     * http://remark.overzealous.com/manual/usage.html
     * @throws IOException
     */
    @Test
    public void test4() throws IOException {
        Remark remark = new Remark();

        String markDown = remark.convert(new File("/Users/chenzhongzheng/Desktop/article_test2.txt"));
        File file = new File("/Users/chenzhongzheng/Desktop/article_test2.md");

        try (
                FileWriter fileWrter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWrter);
                ) {
            bufferedWriter.write(markDown);
        }
        System.out.println(markDown);

    }
}
