package handler.impl;

import handler.Handler;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: chenzhongzheng
 * @desciption:
 */
public class StatisticExtNameHandler implements Handler {
    private Set<String> extensionNameSet = new HashSet<String>();

    public Set<String> getExtensionNameSet() {
        return extensionNameSet;
    }

    public void handle(File file) {
        String[] strs = file.getName().split("\\.");
        String extName = strs[strs.length-1];

        extensionNameSet.add(extName);
    }
}
