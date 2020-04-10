//package com.zkwp.api.config;
//
//import java.io.IOException;
//
//import org.csource.fastdfs.ClientGlobal;
//import org.csource.fastdfs.StorageClient;
//import org.csource.fastdfs.StorageServer;
//import org.csource.fastdfs.TrackerClient;
//import org.csource.fastdfs.TrackerServer;
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.client.RestTemplate;
//
//@Configuration
//public class fastDFSConfig {
//	@Bean
//	@LoadBalanced
//	public RestTemplate getTemplate() { // RestTemplate
//		return new RestTemplate();
//	}
//
//	@Bean
//	public TrackerClient getTrackerClient() {
//		return new TrackerClient();
//	}
//
//	@Bean
//	public TrackerServer getTrackerServer() throws IOException {
//		return this.getTrackerClient().getConnection();
//	}
//
//	@Bean
//	public StorageServer getStorageServer() throws Exception {
//		ClientGlobal.initByProperties("application.properties");
//		StorageServer storageServer = this.getTrackerClient().getStoreStorage(this.getTrackerServer(), "group1");
//		String storageServerIp = storageServer.getSocket().getInetAddress().getHostAddress();
//		return new StorageServer(storageServerIp, 23000, 1);
//	}
//
//	@Bean
//	public StorageClient getStorageClient() throws IOException, Exception {
//		return new StorageClient(this.getTrackerServer(), this.getStorageServer());
//	}
//
//}
