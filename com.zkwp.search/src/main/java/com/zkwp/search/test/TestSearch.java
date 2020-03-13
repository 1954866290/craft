package com.zkwp.search.test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;

import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestSearch {
	@Autowired
	private RestHighLevelClient client;
	@Autowired
	private RestClient restClient;

	// 搜索所有
	@Test
	public void searchAll() throws IOException {
		// 搜索请求对象
		SearchRequest searchRequest = new SearchRequest("wangpeng");
		// 指定类型
		searchRequest.types("doc");
		// 搜索源构建对象
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		// 设置搜索方式 QueryBuilders.matchAllQuery()表示搜索全部
		searchSourceBuilder.query(QueryBuilders.matchAllQuery());
		// 设置源字段过滤 第一个参数表示结果集包括哪些字段，第二个参数表示结果集不包括哪些字段
		searchSourceBuilder.fetchSource(new String[] { "name", "studymodel", "price", "timestamp" }, new String[] {});
		// 向搜索请求对象中设置搜索源构建对象
		searchRequest.source(searchSourceBuilder);
		// 执行搜索,向ES发起http请求
		SearchResponse searchResponse = client.search(searchRequest);
		// 获取搜索结果
		SearchHits hits = searchResponse.getHits();
		// 匹配到的总记录数
		long totalHits = hits.getTotalHits();
		// 得到匹配度高的文档
		SearchHit[] searchHits = hits.getHits();
		// 由于匹配度到的结果是个数组，进行遍历
		for (SearchHit hit : searchHits) {
			String id = hit.getId();// 文档的主键
			Map<String, Object> sourceAsMap = hit.getSourceAsMap();// 将原文档内容转为map得到,然后就可以获取map中的值
			String name = (String) sourceAsMap.get("name");
			// description 虽然有这个字段，但是是获取不到的，因为上面进行了源字段过滤，没有让这个字段显示
			String description = (String) sourceAsMap.get("description");
			String studymodel = (String) sourceAsMap.get("studymodel");
			Double price = (Double) sourceAsMap.get("price");
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			Date timestamp = sdf.parse((String)sourceAsMap.get("timestamp"));
			System.out.println(name);
			System.out.println(description);
			System.out.println(studymodel);
			System.out.println(price);
			// System.out.println(timestamp.getTime());
			System.out.println("--------------");

		}
	}

	// 分页搜索
	@Test
	public void searchBreakPage() throws IOException {
		// 搜索请求对象
		SearchRequest searchRequest = new SearchRequest("wangpeng");
		// 指定类型
		searchRequest.types("doc");
		// 搜索源构建对象
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		int page = 1;
		int size = 1;
		// 这里需要计算出起始记录下标
		int from = (page - 1) * size;
		searchSourceBuilder.from();// 起始记录下标，从0开始
		searchSourceBuilder.size();// 每页显示的记录数
		// 设置搜索方式 QueryBuilders.matchAllQuery()表示搜索全部
		searchSourceBuilder.query(QueryBuilders.matchAllQuery());
		// 设置源字段过滤 第一个参数表示结果集包括哪些字段，第二个参数表示结果集不包括哪些字段
		searchSourceBuilder.fetchSource(new String[] { "name", "studymodel", "price", "timestamp" }, new String[] {});
		// 向搜索请求对象中设置搜索源构建对象
		searchRequest.source(searchSourceBuilder);
		// 执行搜索,向ES发起http请求
		SearchResponse searchResponse = client.search(searchRequest);
		// 获取搜索结果
		SearchHits hits = searchResponse.getHits();
		// 匹配到的总记录数
		long totalHits = hits.getTotalHits();
		// 得到匹配度高的文档
		SearchHit[] searchHits = hits.getHits();
		// 由于匹配度到的结果是个数组，进行遍历
		for (SearchHit hit : searchHits) {
			String id = hit.getId();// 文档的主键
			Map<String, Object> sourceAsMap = hit.getSourceAsMap();// 将原文档内容转为map得到,然后就可以获取map中的值
			String name = (String) sourceAsMap.get("name");
			// description 虽然有这个字段，但是是获取不到的，因为上面进行了源字段过滤，没有让这个字段显示
			String description = (String) sourceAsMap.get("description");
			String studymodel = (String) sourceAsMap.get("studymodel");
			Double price = (Double) sourceAsMap.get("price");
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				Date timestamp = sdf.parse((String)sourceAsMap.get("timestamp"));
			System.out.println(name);
			System.out.println(description);
			System.out.println(studymodel);
			System.out.println(price);
			// System.out.println(timestamp.getTime());
			System.out.println("--------------");

		}

	}

	// 精确搜索，关键字不会再进行分词
	@Test
	public void searchTermQuery() throws IOException {
		// 搜索请求对象
		SearchRequest searchRequest = new SearchRequest("wangpeng");
		// 指定类型
		searchRequest.types("doc");
		// 搜索源构建对象
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		int page = 1;
		int size = 1;
		// 这里需要计算出起始记录下标
		int from = (page - 1) * size;
		searchSourceBuilder.from();// 起始记录下标，从0开始
		searchSourceBuilder.size();// 每页显示的记录数
		// 设置搜索方式 进行精确搜索
		searchSourceBuilder.query(QueryBuilders.termQuery("name", "spring"));
		// 设置源字段过滤 第一个参数表示结果集包括哪些字段，第二个参数表示结果集不包括哪些字段
		searchSourceBuilder.fetchSource(new String[] { "name", "studymodel", "price", "timestamp" }, new String[] {});
		// 向搜索请求对象中设置搜索源构建对象
		searchRequest.source(searchSourceBuilder);
		// 执行搜索,向ES发起http请求
		SearchResponse searchResponse = client.search(searchRequest);
		// 获取搜索结果
		SearchHits hits = searchResponse.getHits();
		// 匹配到的总记录数
		long totalHits = hits.getTotalHits();
		// 得到匹配度高的文档
		SearchHit[] searchHits = hits.getHits();
		// 由于匹配度到的结果是个数组，进行遍历
		for (SearchHit hit : searchHits) {
			String id = hit.getId();// 文档的主键
			Map<String, Object> sourceAsMap = hit.getSourceAsMap();// 将原文档内容转为map得到,然后就可以获取map中的值
			String name = (String) sourceAsMap.get("name");
			// description 虽然有这个字段，但是是获取不到的，因为上面进行了源字段过滤，没有让这个字段显示
			String description = (String) sourceAsMap.get("description");
			String studymodel = (String) sourceAsMap.get("studymodel");
			Double price = (Double) sourceAsMap.get("price");
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				Date timestamp = sdf.parse((String)sourceAsMap.get("timestamp"));
			System.out.println(name);
			System.out.println(description);
			System.out.println(studymodel);
			System.out.println(price);
			// System.out.println(timestamp.getTime());
			System.out.println("--------------");

		}

	}

	// 精确搜索，根据id查询
	@Test
	public void searchTermQueryById() throws IOException {
		// 搜索请求对象
		SearchRequest searchRequest = new SearchRequest("wangpeng");
		// 指定类型
		searchRequest.types("doc");
		// 搜索源构建对象
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		int page = 1;
		int size = 1;
		// 这里需要计算出起始记录下标
		int from = (page - 1) * size;
		searchSourceBuilder.from();// 起始记录下标，从0开始
		searchSourceBuilder.size();// 每页显示的记录数
		// 设置搜索方式 根据id查询
		String[] ids = new String[] { "1", "2" };
		searchSourceBuilder.query(QueryBuilders.termsQuery("_id", ids));
		// 设置源字段过滤 第一个参数表示结果集包括哪些字段，第二个参数表示结果集不包括哪些字段
		searchSourceBuilder.fetchSource(new String[] { "name", "studymodel", "price", "timestamp" }, new String[] {});
		// 向搜索请求对象中设置搜索源构建对象
		searchRequest.source(searchSourceBuilder);
		// 执行搜索,向ES发起http请求
		SearchResponse searchResponse = client.search(searchRequest);
		// 获取搜索结果
		SearchHits hits = searchResponse.getHits();
		// 匹配到的总记录数
		long totalHits = hits.getTotalHits();
		// 得到匹配度高的文档
		SearchHit[] searchHits = hits.getHits();
		// 由于匹配度到的结果是个数组，进行遍历
		for (SearchHit hit : searchHits) {
			String id = hit.getId();// 文档的主键
			Map<String, Object> sourceAsMap = hit.getSourceAsMap();// 将原文档内容转为map得到,然后就可以获取map中的值
			String name = (String) sourceAsMap.get("name");
			// description 虽然有这个字段，但是是获取不到的，因为上面进行了源字段过滤，没有让这个字段显示
			String description = (String) sourceAsMap.get("description");
			String studymodel = (String) sourceAsMap.get("studymodel");
			Double price = (Double) sourceAsMap.get("price");
//						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//						Date timestamp = sdf.parse((String)sourceAsMap.get("timestamp"));
			System.out.println(name);
			System.out.println(description);
			System.out.println(studymodel);
			System.out.println(price);
			// System.out.println(timestamp.getTime());
			System.out.println("--------------");

		}

	}

	// 全文检索，会将搜索内容进行分词
	@Test
	public void searchMatchQuery() throws IOException {
		// 搜索请求对象
		SearchRequest searchRequest = new SearchRequest("wangpeng");
		// 指定类型
		searchRequest.types("doc");
		// 搜索源构建对象
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		// 设置搜索方式 进行全文搜索
		searchSourceBuilder.query(QueryBuilders.matchQuery("description", "spring开发框架").minimumShouldMatch("80%"));
		// 设置源字段过滤 第一个参数表示结果集包括哪些字段，第二个参数表示结果集不包括哪些字段
		searchSourceBuilder.fetchSource(new String[] { "name", "studymodel", "price", "timestamp" }, new String[] {});
		// 向搜索请求对象中设置搜索源构建对象
		searchRequest.source(searchSourceBuilder);
		// 执行搜索,向ES发起http请求
		SearchResponse searchResponse = client.search(searchRequest);
		// 获取搜索结果
		SearchHits hits = searchResponse.getHits();
		// 匹配到的总记录数
		long totalHits = hits.getTotalHits();
		// 得到匹配度高的文档
		SearchHit[] searchHits = hits.getHits();
		// 由于匹配度到的结果是个数组，进行遍历
		for (SearchHit hit : searchHits) {
			String id = hit.getId();// 文档的主键
			Map<String, Object> sourceAsMap = hit.getSourceAsMap();// 将原文档内容转为map得到,然后就可以获取map中的值
			String name = (String) sourceAsMap.get("name");
			// description 虽然有这个字段，但是是获取不到的，因为上面进行了源字段过滤，没有让这个字段显示
			String description = (String) sourceAsMap.get("description");
			String studymodel = (String) sourceAsMap.get("studymodel");
			Double price = (Double) sourceAsMap.get("price");
//					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//					Date timestamp = sdf.parse((String)sourceAsMap.get("timestamp"));
			System.out.println(name);
			System.out.println(description);
			System.out.println(studymodel);
			System.out.println(price);
			// System.out.println(timestamp.getTime());
			System.out.println("--------------");

		}

	}
	
	// 全文检索，会将搜索内容进行分词,并可以同时指定多个字段进行搜索
		@Test
		public void searchMultiQuery() throws IOException {
			// 搜索请求对象
			SearchRequest searchRequest = new SearchRequest("wangpeng");
			// 指定类型
			searchRequest.types("doc");
			// 搜索源构建对象
			SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
			// 设置搜索方式 全文检索，会将搜索内容进行分词,并可以同时指定多个字段进行搜索,并可以提升某个字段中内容的权重
			// 下面就是将如果在name中出现的权重提升10倍，搜索结果将会提前
			searchSourceBuilder.query(QueryBuilders.multiMatchQuery("spring css", "name","description").minimumShouldMatch("50%").field("name", 10));
			// 设置源字段过滤 第一个参数表示结果集包括哪些字段，第二个参数表示结果集不包括哪些字段
			searchSourceBuilder.fetchSource(new String[] { "name", "studymodel", "price", "timestamp" }, new String[] {});
			// 向搜索请求对象中设置搜索源构建对象
			searchRequest.source(searchSourceBuilder);
			// 执行搜索,向ES发起http请求
			SearchResponse searchResponse = client.search(searchRequest);
			// 获取搜索结果
			SearchHits hits = searchResponse.getHits();
			// 匹配到的总记录数
			long totalHits = hits.getTotalHits();
			// 得到匹配度高的文档
			SearchHit[] searchHits = hits.getHits();
			// 由于匹配度到的结果是个数组，进行遍历
			for (SearchHit hit : searchHits) {
				String id = hit.getId();// 文档的主键
				Map<String, Object> sourceAsMap = hit.getSourceAsMap();// 将原文档内容转为map得到,然后就可以获取map中的值
				String name = (String) sourceAsMap.get("name");
				// description 虽然有这个字段，但是是获取不到的，因为上面进行了源字段过滤，没有让这个字段显示
				String description = (String) sourceAsMap.get("description");
				String studymodel = (String) sourceAsMap.get("studymodel");
				Double price = (Double) sourceAsMap.get("price");
//						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//						Date timestamp = sdf.parse((String)sourceAsMap.get("timestamp"));
				System.out.println(name);
				System.out.println(description);
				System.out.println(studymodel);
				System.out.println(price);
				// System.out.println(timestamp.getTime());
				System.out.println("--------------");

			}

		}


}