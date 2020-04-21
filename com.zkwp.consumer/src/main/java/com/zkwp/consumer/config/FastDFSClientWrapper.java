package com.zkwp.consumer.config;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @auther zhangkun
 * @date 2020/4/10 20:36
 **/
@Component
public class FastDFSClientWrapper {

    private final Logger logger = LoggerFactory.getLogger(FastDFSClientWrapper.class);
    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    /**
     * 文件上传     * 最后返回fastDFS中的文件名称;group1/M00/01/04/CgMKrVvS0geAQ0pzAACAAJxmBeM793.doc     *     * @param bytes     文件字节     * @param fileSize  文件大小     * @param extension 文件扩展名     * @return fastDfs路径
     */
    public String uploadFile(byte[] bytes, long fileSize, String extension) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        StorePath storePath = fastFileStorageClient.uploadFile(byteArrayInputStream, fileSize, extension, null);
        System.out.println(storePath.getGroup() + "==" + storePath.getPath() + "======" + storePath.getFullPath());
        return storePath.getFullPath();
    }

    /**
     * 下载文件     *  返回文件字节流大小     * @param fileUrl 文件URL     * @return 文件字节     * @throws IOException
     */
    public byte[] downloadFile(String fileUrl) throws IOException {
        String group = fileUrl.substring(0, fileUrl.indexOf("/"));
        String path = fileUrl.substring(fileUrl.indexOf("/") + 1);
        DownloadByteArray downloadByteArray = new DownloadByteArray();
        byte[] bytes = fastFileStorageClient.downloadFile(group, path, downloadByteArray);
        return bytes;
    }

}
