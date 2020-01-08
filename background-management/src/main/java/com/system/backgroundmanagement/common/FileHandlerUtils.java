package com.system.backgroundmanagement.common;

import com.system.backgroundmanagement.service.exception.ServiceException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
     * @param fileBytes 文件字节流
     * @param fileName  保存文件的名
     * @param savePath  保存路径
     * @return 保存结果
     * @throws IOException IO异常
     */
    public static boolean saveFileToDisk(byte[] fileBytes, String fileName, String savePath) throws IOException {
        File file = new File(savePath);
        System.out.println(file.getCanonicalPath());
        boolean exists = file.exists();
        if (!exists) {
            if (!file.mkdirs()) {
                throw new IOException("dir create failed");
            }
        }
        try (FileOutputStream outputStream = new FileOutputStream(savePath + fileName)) {
            outputStream.write(fileBytes);
            return true;
        }
    }

    /**
     * 从磁盘上删除指定路径的文件
     *
     * @param filePath 文件路径
     * @return 删除结果
     */
    public static boolean deleteFileFromDisk(String filePath) {
        File file = new File(filePath);
        if (file.isDirectory()) {
            throw new ServiceException("the path is not file");
        }
        if (file.exists()) {
            return file.delete();
        }
        return !file.exists();
    }

    ///////////////////////////////////////////////////////////////////////////
    // TODO: 2020/1/8 文件下载功能,待实现
    ///////////////////////////////////////////////////////////////////////////
}
