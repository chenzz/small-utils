package com.test;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author: chenzhongzheng
 * @desciption:
 * @date: 2016-11-27
 */
public class FileTest {
    @Test
    public void test1() throws IOException {
        File file = new File("/Users/chenzhongzheng/Desktop/a:");
        try (
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                ) {
            bufferedWriter.write("test");
        }
    }
}
