package com.system.backgroundmanagement.common;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * 文件相关处理工具类
 *
 * @author luoyuequan
 * @time 2020/1/8 10:03
 */
public class FileHandlerUtils {
    /**
     * 保存文件到磁盘
     *
     * @param file     文件字节流
     * @param fileName 保存文件的名
     * @param savePath 保存路径
     */
    public static void saveFileToDisk(InputStream file, String fileName, String savePath) throws IOException {
        //项目的resource路径
        String resourcePath = ResourceUtils.getURL("classpath:").getPath();
        //新文件的路径
        File newFile = new File(resourcePath + savePath, fileName);
        //文件父级文件夹不存在
        if (!newFile.getParentFile().exists()) {
            //创建父级文件夹
            boolean mkdirs = newFile.getParentFile().mkdirs();
            if (!mkdirs) {
                //文件夹创建失败
                throw new FileNotFoundException("mkdirs failed");
            }
        }
        Files.copy(file, newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * 从磁盘上删除指定路径的文件
     *
     * @param filePath 文件路径
     */
    public static void deleteFileFromDisk(String filePath) throws IOException {
        //项目的resource路径
        String resourcePath = ResourceUtils.getURL("classpath:").getPath();
        Files.deleteIfExists(Paths.get(resourcePath + filePath));
    }

    ///////////////////////////////////////////////////////////////////////////
    // TODO: 2020/1/8 文件下载功能,待实现
    ///////////////////////////////////////////////////////////////////////////
}
