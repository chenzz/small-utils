package utils;

import contants.ExtNameConstant;
import handler.Handler;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.File;

/**
 * @author: chenzhongzheng
 * @desciption:
 */
public class FileUtil {

    //递归文件夹下所有文件, 并调用Hanlder 中的回调函数进行处理
    public static void recurseFile(File file, Handler handler) {
        if (file.isFile()) {
            handler.handle(file);
        } else {
            for (File childFile : file.listFiles()) {
                recurseFile(childFile, handler);
            }
        }
    }

    //递归遍历某个文件夹下的所有文件夹
    public static void iterateDir(File dir, Handler handler) {
        if (dir.isFile()) {
            throw new RuntimeException(dir.getAbsolutePath() + " should be a directory!");
        }

        //遍历所有文件
        for (File childFile : dir.listFiles()) {
            //如果是文件夹则进行处理
            if (childFile.isDirectory()) {
                handler.handle(childFile);
            }
        }
    }

    //递归遍历某个文件夹下的所有文件夹
    public static void iterateDirExceptSpetial(File dir, Handler handler) {

        if (dir.isFile()) {
            throw new RuntimeException(dir.getAbsolutePath() + " should be a directory!");
        }

        //遍历所有文件
        for (File childFile : dir.listFiles()) {
            //如果是文件夹则进行处理
            if (childFile.isDirectory() && !childFile.getName().matches(ExtNameConstant.SPETIAL_DIR)) {
                handler.handle(childFile);
            }
        }
    }

    //解压zip文件
    public static void unzipFile(String source, String destination) {
        String password = "password";

        try {
            ZipFile zipFile = new ZipFile(source);
            if (zipFile.isEncrypted()) {
                zipFile.setPassword(password);
            }
            zipFile.extractAll(destination);
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }
}
