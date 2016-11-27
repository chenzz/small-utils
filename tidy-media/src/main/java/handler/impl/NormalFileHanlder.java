package handler.impl;

import contants.ExtNameConstant;
import handler.Handler;

import java.io.File;

/**
 * @author: chenzhongzheng
 * @desciption:
 */
public class NormalFileHanlder implements Handler {

    public void handle(File file) {
        //如果是视频
        if (file.getName().matches(ExtNameConstant.VIDEO_EXT)) {
            file.renameTo(new File(ExtNameConstant.MEDIA_PATH + "/" + file.getName()));
        }

        //如果是种子
        else if (file.getName().matches(ExtNameConstant.TORRENT_EXT)) {
            file.renameTo(new File(ExtNameConstant.TORRENT_PATH + "/" + file.getName()));
        }

        //如果是图片
        else if (file.getName().matches(ExtNameConstant.PIC_EXT)) {
            file.renameTo(new File(ExtNameConstant.PICTURE_PATH + "/" + file.getName()));
        }
    }
}
