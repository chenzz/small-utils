
import contants.ExtNameConstant;
import handler.impl.*;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import utils.FileUtil;
import utils.RarUtil;

import java.io.File;
import java.io.IOException;

/**
 * @author: chenzhongzheng
 * @desciption: 整理 ~/Media文件夹, 使其:
 * 1. 所有的压缩包进行解压, 并删除压缩包
 * 2. 所有的视频 移动到 ~/Media 目录下
 * 3. 所有的种子 移动到 ~/Media/1-torrent 文件下
 * 4. 所有的图片 移动到 ~/Media/2-picture 文件下
 * 5. 除了特殊的几个文件夹外, ~/Media下其他所有的文件夹都删除

 */
public class TidyMedia {

    //联系ls的功能
    @Test
    public void test() {
        String[] fileList = new File(ExtNameConstant.MEDIA_PATH).list();
        for (String file : fileList) {
            System.out.println(file);
        }
    }

    //打印所有文件名
    @Test
    public void test2() {
        FileUtil.recurseFile(new File(ExtNameConstant.MEDIA_PATH), new PrintHanlder());
    }

    //打印所有拓展名
    @Test
    public void test3() {
        StatisticExtNameHandler handler = new StatisticExtNameHandler();
        FileUtil.recurseFile(new File(ExtNameConstant.MEDIA_PATH), handler);
        for (String extName : handler.getExtensionNameSet()) {
            System.out.println(extName);
        }
    }

    //测试解压zip文件
    @Test
    public void test4() {
        String source = "/Users/chenzhongzheng/Downloads/1471115753_www_wofun_cc_MDXXH.zip";
        String destination = "/Users/chenzhongzheng/Downloads";

        FileUtil.unzipFile(source, destination);
    }

    //测试解压rar文件
    @Test
    public void test5() {
        String source = "/Users/chenzhongzheng/Downloads/a.rar";
        String destination = "/Users/chenzhongzheng/Downloads";

        RarUtil.extractArchive(source, destination);
    }

    //测试打印某文件夹下所有文件夹
    @Test
    public void test6() throws IOException {
        FileUtil.iterateDirExceptSpetial(new File(ExtNameConstant.INBOX_PATH), new PrintHanlder());
    }


    //最终版本
    @Test
    public void test7() throws IOException {
        FileUtil.recurseFile(new File(ExtNameConstant.INBOX_PATH), new CompressedFileHanlder());
        FileUtil.recurseFile(new File(ExtNameConstant.INBOX_PATH), new NormalFileHanlder());
        FileUtil.iterateDirExceptSpetial(new File(ExtNameConstant.INBOX_PATH), new DeleteHandler());
    }

}
