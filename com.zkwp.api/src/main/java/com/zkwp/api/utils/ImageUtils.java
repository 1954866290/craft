package com.zkwp.api.utils;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.csource.fastdfs.ClientGlobal;
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

	private static TrackerClient trackerClient = null;

	private static TrackerServer trackerServer = null;

	private static StorageServer storageServer = null;

	private static StorageClient storageClient = null;

	@Value("${fastdfs.groupname}")

	private static String groupName;

	@Value("${fastdfs.prefixpath}")

	private static String prefixPath;

	private static Logger log = LoggerFactory.getLogger(ImageUtils.class);

	static {

		try {

			ClientGlobal.initByProperties("classpath:application.properties");

			trackerClient = new TrackerClient();

			trackerServer = trackerClient.getConnection();

			String storageServerIp = getStorageServerIp(trackerClient, trackerServer);

			storageServer = getStorageServer(storageServerIp);

			storageClient = new StorageClient(trackerServer, storageServer);

		} catch (Exception e) {

		}

	}

	public static boolean deleteFile(String filePath) throws Exception {

		return storageClient.delete_file(groupName, filePath) == 1 ? true : false;

	}

	public static void downloadFile(String filePath) throws Exception {

		byte[] b = storageClient.download_file(groupName, filePath);

		if (b == null) {

			throw new IOException("文件" + filePath + "不存在");

		}

		FileOutputStream fileOutputStream = null;

		try {

			String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);

			fileOutputStream = new FileOutputStream("c://" + fileName);

			IOUtils.write(b, fileOutputStream);

		} finally {

			fileOutputStream.close();

		}

	}

	public static String uploadFile(byte[] bytesFile, String ext, String fileName) throws Exception {

		StringBuffer completePath = new StringBuffer();
		
		completePath.append(joinString(storageClient.upload_file(bytesFile, ext, null)));
		
		return completePath.toString();

	}

	public static StringBuffer joinString(String[] path) {

		StringBuffer result = new StringBuffer();

		for (int i = 0; i < path.length - 1; i++) {

			result.append(path[i] + "/");

		}

		result.append(path[path.length - 1]);

		return result;

	}

	/**
	 * 
	 * 得到Storage服务
	 *
	 *
	 * 
	 * 
	 * 
	 * @param storageIp
	 * 
	 * @return 返回Storage服务
	 * 
	 **/

	private static StorageServer getStorageServer(String storageIp) {

		StorageServer storageServer = null;

		if (storageIp != null && !("").equals(storageIp)) {

			try {

				// ip port store_path下标

				storageServer = new StorageServer(storageIp, 23000, 1);

			} catch (IOException e) {

				e.printStackTrace();

			}

		}

		log.debug("——storage server生成");

		return storageServer;

	}

	/**
	 * 
	 * 获得可用的storage IP
	 *
	 * 
	 * 
	 * @param trackerClient
	 * 
	 * @param trackerServer
	 * 
	 * @return 返回storage IP
	 * 
	 */

	private static String getStorageServerIp(TrackerClient trackerClient, TrackerServer trackerServer) {

		String storageIp = null;

		if (trackerClient != null && trackerServer != null) {

			try {

				StorageServer storageServer = trackerClient.getStoreStorage(trackerServer, "group1");

				storageIp = storageServer.getSocket().getInetAddress().getHostAddress();

			} catch (IOException e) {

				e.printStackTrace();

			}

		}

		log.debug("——获取组中可用的storage IP——" + storageIp);

		return storageIp;

	}

}
