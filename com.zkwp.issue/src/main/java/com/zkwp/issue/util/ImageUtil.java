package com.zkwp.issue.util;

import com.zkwp.issue.config.FastDFSClientWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @auther zhangkun
 * @date 2020/4/11 10:51
 **/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
@Service
public class ImageUtil {
    @Autowired
    private FastDFSClientWrapper fastDFSClientWrapper;
    private final Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    /**
     * 文件上传     *  最后返回fastDFS中的文件名称;group1/M00/01/04/CgMKrVvS0geAQ0pzAACAAJxmBeM793.doc     * @param file 页面提交时文件     * @return
     */
    public String uploadFile(MultipartFile file) {
        byte[] bytes = new byte[0];
        try {
            bytes = file.getBytes();
        } catch (IOException e) {
            logger.error("获取文件错误");
            e.printStackTrace();
        }        //获取源文件名称
        String originalFileName = file.getOriginalFilename();
        //获取文件后缀--.doc
        String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        String fileName = file.getName();        //获取文件大小
        long fileSize = file.getSize();
        System.out.println(originalFileName + "==" + fileName + "==" + fileSize + "==" + extension + "==" + bytes.length);
        return fastDFSClientWrapper.uploadFile(bytes, fileSize, extension);
    }


}
