package handler.impl;

import handler.Handler;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import utils.FileUtil;
import utils.RarUtil;

import java.io.File;
import java.io.IOException;

/**
 * @author: chenzhongzheng
 * @desciption:
 */
public class CompressedFileHanlder implements Handler {

    public void handle(File file) {

        try {
            //如果是压缩文件, 则解压后删除
            if (file.getName().matches(".+\\.zip")) {
                String source = file.getAbsolutePath();
                String destination = file.getCanonicalPath();
                String password = "password";

                FileUtil.unzipFile(source, destination);
                file.delete();
            }
            //如果发现大于10M的文件则退出
            else if (file.getName().matches(".+\\.rar")) {
                RarUtil.extractArchive(file.getAbsoluteFile(), file.getCanonicalFile());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
