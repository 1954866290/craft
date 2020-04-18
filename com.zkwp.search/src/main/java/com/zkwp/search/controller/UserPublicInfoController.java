package com.zkwp.search.controller;

import com.zkwp.api.bean.*;
import com.zkwp.api.utils.StringUtil;
import com.zkwp.search.service.UserPublicInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@Api("UserPublicInfoController相关的api")
public class UserPublicInfoController {
	
	@Autowired
	private UserPublicInfoService userPublicInfoService;
	
	@Autowired
	RestHighLevelClient client;
	
	@Value("${wangpeng.craft.index}")
	private String index;
	@Value("${wangpeng.craft.type}")
	private String type;
	@Value("${wangpeng.craft.source_field}")
	private String source_field;
	
	/*
	 * userId:用户id，worksId：作品id
	 * 在发布之后，分别根据两个id去查询信息，并将信息复制到userPublic中
	 */
	@RequestMapping(value = "/issueAfter", method = RequestMethod.POST)
	@ResponseBody
	public void issueAfter(String userid, String worksid) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		UserPublic userPub = new UserPublic();
		Issue issue = userPublicInfoService.getWorksInfoById(worksid);
		userPub = userPublicInfoService.getIssueInfoByWorksId(worksid);
		User user = userPublicInfoService.getUserInfoById(userid);
	    //将查询出来的用户属性信息复制到userPub相同的属性中
        BeanUtils.copyProperties(user, userPub);
        if (StringUtil.isBlank(userPub.getUsername())) {
			BeanUtils.copyProperties(issue, userPub);
			userPub.setWorksid(worksid);
			userPub.setIssueUpdatedTime(sdf.format(new Date()));
			userPublicInfoService.saveUserPubInfo(userPub);
		} else {
			BeanUtils.copyProperties(issue, userPub);
			userPub.setWorksid(worksid);
			userPub.setIssueUpdatedTime(sdf.format(new Date()));
			userPublicInfoService.updateUserPubInfoByWorksId(userPub);
		}
		
	}
	
	/*
	 * 使用elasticsearch的API进行去索引库搜索
	 */
	@RequestMapping(value="/searchInfo")
	@ResponseBody
	@ApiOperation(value = "搜索用户及其作品信息", notes = "可根据关键字，分类，价格区间进行搜索")
	public OutputObject SearchInfo(int page, int size, SearchParam searchParam) {
		OutputObject out = new OutputObject();
		List<UserPublic> lists = new ArrayList<>();
		// 1.创建搜索请求对象
		SearchRequest searchRequest = new SearchRequest(index);
		// 2.设置搜索类型
		searchRequest.types(type);
		// 3.构建搜索源对象
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		// 4.设置源字段过滤 第一个参数表示结果集包括哪些字段，第二个参数表示结果集不包括哪些字段
		String[] source_field_array = source_field.split(",");
		searchSourceBuilder.fetchSource(source_field_array, new String[] {});
		// 创建布尔查询对象
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		// 5.设置搜索条件 根据关键字搜索
		if (StringUtil.isNotBlank(searchParam.getKeyword())) {
			MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(searchParam.getKeyword(), "username","title","description")
			.minimumShouldMatch("70%")
			.field("title",10);
			boolQueryBuilder.must(multiMatchQueryBuilder);
		}
		// 根据分类进行搜索
		if (StringUtil.isNotBlank(searchParam.getType())) {
			boolQueryBuilder.filter(QueryBuilders.termQuery("type", searchParam.getType()));
		}
		// 根据价格区间进行搜索
		if (StringUtil.isNotBlank(searchParam.getPrice_min()) && StringUtil.isNotBlank(searchParam.getPrice_max())) {
			Double min = Double.parseDouble(searchParam.getPrice_min());
			Double max = Double.parseDouble(searchParam.getPrice_max());
			boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").gte(min).lte(max));
		}
		// 设置boolQuery到searchSourceBuilder中
		searchSourceBuilder.query(boolQueryBuilder);
		searchRequest.source(searchSourceBuilder);
		// 执行搜索
		try {
			SearchResponse searchResponse = client.search(searchRequest);
			// 获取响应结果
			SearchHits searchHits = searchResponse.getHits();
			// 得到匹配的总记录数
			long totalHits = searchHits.getTotalHits();
			out.setSearchTotals(totalHits);
			// 得到匹配度高的记录
			SearchHit[] hits = searchHits.getHits();
			for (SearchHit hit : hits) {
				UserPublic userPublic = new UserPublic();
				// 自动转为map的源文档
				Map<String, Object> sourceAsMap = hit.getSourceAsMap();
				String username = (String) sourceAsMap.get("username");
				userPublic.setUsername(username);
				String title = (String) sourceAsMap.get("title");
				userPublic.setTitle(title);
				String description = (String) sourceAsMap.get("description");
				userPublic.setDescription(description);
				String viewcount = (String) sourceAsMap.get("viewcount");
				userPublic.setViewcount(viewcount);
				String charmingcount = (String) sourceAsMap.get("charmingcount");
				userPublic.setCharmingcount(charmingcount);
				String issueCreatedTime = (String) sourceAsMap.get("issueCreatedTime");
				userPublic.setIssueCreatedTime(issueCreatedTime);
				String oneimagepath = (String) sourceAsMap.get("oneimagepath");
				userPublic.setOneimagepath(oneimagepath);
				String twoimagepath = (String) sourceAsMap.get("twoimagepath");
				userPublic.setTwoimagepath(twoimagepath);
				String threeimagepath = (String) sourceAsMap.get("threeimagepath");
				userPublic.setThreeimagepath(threeimagepath);
				String fourimagepath = (String) sourceAsMap.get("fourimagepath");
				userPublic.setFourimagepath(fourimagepath);
				String videopath = (String) sourceAsMap.get("videopath");
				userPublic.setVideopath(videopath);
				String price = (String) sourceAsMap.get("price");
				userPublic.setPrice(price);
				lists.add(userPublic);
			}
			out.setList(lists);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out;
	}

}
