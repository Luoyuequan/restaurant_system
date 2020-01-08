package com.system.backgroundmanagement.common;

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
        Files.copy(file, Paths.get(savePath + fileName), StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * 从磁盘上删除指定路径的文件
     *
     * @param filePath 文件路径
     */
    public static void deleteFileFromDisk(String filePath) throws IOException {
        Files.deleteIfExists(Paths.get(filePath));
    }

    ///////////////////////////////////////////////////////////////////////////
    // TODO: 2020/1/8 文件下载功能,待实现
    ///////////////////////////////////////////////////////////////////////////
}
