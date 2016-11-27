package handler.impl;

import handler.Handler;

import java.io.File;

/**
 * @author: chenzhongzheng
 * @desciption:
 */
public class PrintHanlder implements Handler {

    public void handle(File file) {

        System.out.println(file.getAbsolutePath());
    }
}
