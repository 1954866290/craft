package com.zkwp.api.utils;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * @auther zhangkun
 * @date 2020/2/18 21:52
 **/

public class ImageUtils {

	@Autowired
    private static TrackerClient trackerClient ;
	@Autowired
    private static TrackerServer trackerServer ;
	@Autowired
    private static StorageServer storageServer ;
	@Autowired
    private static StorageClient storageClient ;

    @Value("${fastdfs.groupname}")
    private static String groupName  ;

    @Value("${fastdfs.prefixpath}")
    private static String prefixPath;

    private static Logger log =LoggerFactory.getLogger(ImageUtils.class);

    

    public static boolean deleteFile(String filePath) throws Exception{
        return storageClient.delete_file(groupName, filePath)==1?true:false;
    }


    public static void downloadFile(String filePath)throws Exception{
        byte[] b = storageClient.download_file(groupName, filePath);
        if (b == null) {
            throw new IOException("文件" + filePath + "不存在");
        }
        FileOutputStream fileOutputStream= null;
        try {
            String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
            fileOutputStream = new FileOutputStream("c://" + fileName);
            IOUtils.write(b, fileOutputStream);
        }finally {
            fileOutputStream.close();
        }

    }

    public static String  uploadFile(byte[] bytesFile,String ext,String fileName) throws Exception{
        StringBuffer completePath = new StringBuffer();
        completePath.append(joinString(storageClient.upload_file(bytesFile,ext,null)));
        return completePath.toString();
    }


    public static StringBuffer joinString(String[] path){
        StringBuffer result = new StringBuffer();
        for(int i=0;i<path.length-1;i++){
            result.append(path[i]+"/");
        }
        result.append(path[path.length-1]);
        return result;
    }
 
 

}
