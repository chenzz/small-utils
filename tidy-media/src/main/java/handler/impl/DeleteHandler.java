package handler.impl;

import handler.Handler;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author: chenzhongzheng
 * @desciption:
 */
public class DeleteHandler implements Handler {
    public void handle(File file) {
        try {
            FileUtils.deleteDirectory(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
