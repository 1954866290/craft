package com.zkwp.search.test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestIndex {
	@Autowired
	private RestHighLevelClient client;
	@Autowired
	private RestClient restClient;

	// 创建索引库
	@Test
	public void createIndexTest() throws IOException {
		// 1.创建索引对象
		CreateIndexRequest createIndexRequest = new CreateIndexRequest("aaa");
		// 2.设置索引参数
		createIndexRequest.settings(Settings.builder().put("number_of_shards", "1").put("number_of_replicas", "0"));
		// 设置映射
		createIndexRequest
				.mapping("doc","{\"properties\": {\"name\": {\"type\": \"text\",\"analyzer\":\"ik_max_word\",\"search_analyzer\":\"ik_smart\"\r\n" + 
						"},\"description\": {\"type\": \"text\",\"analyzer\":\"ik_max_word\",\"search_analyzer\":\"ik_smart\"\r\n" + 
						"},\"pic\":{\"type\":\"text\",\"index\":false},\"studymodel\":{\"type\":\"text\"}}}",
						XContentType.JSON);
		// 3.创建操作索引的客户端
		IndicesClient indices = client.indices();
		// 3.执行删除索引/
		CreateIndexResponse createIndexResponse = indices.create(createIndexRequest);
		// 4.得到执行结果
		boolean acknowledged = createIndexResponse.isAcknowledged();
		System.out.println(acknowledged);

	}

	// 删除索引库
	@Test
	public void deleteIndexTest() throws IOException {
		// 1.创建删除索引对象
		DeleteIndexRequest request = new DeleteIndexRequest("aaa");
		// 2.创建操作索引的客户端
		IndicesClient indices = client.indices();
		// 3.执行删除索引/
		DeleteIndexResponse deleteIndexResponse = indices.delete(request);
		// 4.得到执行结果
		boolean acknowledged = deleteIndexResponse.isAcknowledged();
		System.out.println(acknowledged);

	}

	// 添加文档
	@Test
	public void addTestDoc() throws IOException {
		 Map<String, Object> jsonMap = new HashMap<String, Object>();
		 jsonMap.put("name", "springcloud实战");
		 jsonMap.put("description", "本课程主要从四个章节进行讲解： 1.微服务架构入门 2.spring cloud 基础入门 3.实战Spring Boot 4.注册中心eureka。");
		 jsonMap.put("studymodel", "201001");
		 SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy‐MM‐dd HH:mm:ss");
		 jsonMap.put("timestamp", dateFormat.format(new Date()));
		 jsonMap.put("price", 5.6f);
		 //索引请求对象
		IndexRequest indexRequest = new IndexRequest("aaa","doc");
		//指定索引文档内容
		indexRequest.source(jsonMap);
	    //索引响应对象
		 IndexResponse indexResponse = client.index(indexRequest);
		 //获取响应结果
		 DocWriteResponse.Result result = indexResponse.getResult();
		 System.out.println(result);
	}
	
	//查询文档
	@Test
	public void getDoc() throws IOException {
		// 创建查询请求对象
		GetRequest getRequest = new GetRequest("aaa","doc","rrnupHABqlrPGhoXs8q1");
		GetResponse getResponse = client.get(getRequest);
		boolean exists = getResponse.isExists();
		// 得到文档内容
		Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
		System.out.println(sourceAsMap);
	}
	
	// 更新文档
	@Test
	public void updateDoc() throws IOException {
	UpdateRequest updateRequest = new UpdateRequest("aaa", "doc", "rrnupHABqlrPGhoXs8q1");
	Map<String, String> map = new HashMap<>();
	map.put("name", "spring cloud实战");
	updateRequest.doc(map);
	UpdateResponse update = client.update(updateRequest);
	RestStatus status = update.status();
	System.out.println(status);
	}
	
	// 删除文档
	@Test
	public void testDelDoc() throws IOException {
	//删除文档,根据id
	String id = "rrnupHABqlrPGhoXs8q1";
	//删除索引请求对象
	DeleteRequest deleteRequest = new DeleteRequest("aaa","doc",id);
	//响应对象
	DeleteResponse deleteResponse = client.delete(deleteRequest);
	//获取响应结果
	DocWriteResponse.Result result = deleteResponse.getResult();
	System.out.println(result);
	}

}