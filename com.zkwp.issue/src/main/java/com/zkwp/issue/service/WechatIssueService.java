package com.zkwp.issue.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.zkwp.api.bean.Issue;
import com.zkwp.api.bean.OutputObject;
import com.zkwp.api.utils.StringUtil;
import com.zkwp.issue.dao.WechatIssueDao;
import com.zkwp.issue.util.JsonUtil;

@Service
public class WechatIssueService {
	
	@Autowired
	private WechatIssueDao wechatIssueDao;
	
	/**
	 * 视频发布
	 * @param params
	 * @return
	 */
	public OutputObject uploadVideo(Map params) {
		OutputObject out = new OutputObject();
        String videoPath = StringUtil.objToString(params.get("videoPath"));
        String userid = StringUtil.objToString(params.get("userId"));
        Issue issue = new Issue();
        issue.setCreatedtime(StringUtil.dateToString(new Date()));
        issue.setTitle(StringUtil.objToString(params.get("title")));
        issue.setDescription(StringUtil.objToString(params.get("description")));
        issue.setUserid(userid);
        issue.setPrice(StringUtil.objToString(params.get("price")));
        issue.setType(StringUtil.objToString(params.get("type")));
        issue.setVideopath(videoPath);
        int i = wechatIssueDao.saveIssueRecord(issue);
        if (1 == i) {
        	out.setReturnCode("0000");
        	out.setReturnMessage("视频上传成功");
        }
        return out;
    }
	
	/**
	 * 图片发布
	 * @param params
	 * @return
	 */
	public OutputObject uploadPics(String imagePath, Map params) {
		OutputObject out = new OutputObject();
		List imagesPath = (List) JsonUtil.convertJson2Object(imagePath, List.class);
        String userid = StringUtil.objToString(params.get("userId"));
        Issue issue = new Issue();
        for (int i=0; i<imagesPath.size(); i++) {
        	if (i==0) {
        		issue.setOneimagepath((String)imagesPath.get(i));
        	}
        	if (i==1) {
        		issue.setTwoimagepath((String)imagesPath.get(i));
        	}
        	if (i==2) {
        		issue.setThreeimagepath((String)imagesPath.get(i));
        	}
        	if (i==3) {
        		issue.setFourimagepath((String)imagesPath.get(i));
        	}
        }
        issue.setCreatedtime(StringUtil.dateToString(new Date()));
        issue.setTitle(StringUtil.objToString(params.get("title")));
        issue.setDescription(StringUtil.objToString(params.get("description")));
        issue.setUserid(userid);
        issue.setPrice(StringUtil.objToString(params.get("price")));
        issue.setType(StringUtil.objToString(params.get("type")));
        int i = wechatIssueDao.saveImagesRecord(issue);
        if (1 == i) {
        	out.setReturnCode("0000");
        	out.setReturnMessage("图片上传成功");
        }
        return out;
    }
	
	/**
	 * 根据手机号获取用户id
	 * @param phone
	 * @return
	 */
	public String getUserIdByPhone(String phone) {
		String userId = wechatIssueDao.getUserIdByPhone(phone);
		return userId;
	}
	
	/**
	 * 根据用户手机号获取用户已发布的作品总数
	 * @param userId
	 * @return
	 */
	public OutputObject getIssuesByUserId(String userId) {
		OutputObject out = new OutputObject();
		List<Issue> issueList = wechatIssueDao.getIssuesById(userId);
		out.setReturnList(issueList);
		return out;
	}
	
	/**
	 * 根据用户手机号获取用户已发布的作品
	 * @param userId
	 * @return
	 */
	public OutputObject getIssuesByUserIdAll(Map params) {
		OutputObject out = new OutputObject();
		String pageNum = StringUtil.objToString(params.get("pageNum"));
		String pageSize = StringUtil.objToString(params.get("pageSize"));
		String userId = StringUtil.objToString(params.get("userId"));
		int pagenum = Integer.parseInt(pageNum);
		int pagesize = Integer.parseInt(pageSize);
		PageHelper.startPage(pagenum, pagesize);
		List<Issue> issueList = wechatIssueDao.getIssuesById(userId);
		out.setReturnList(issueList);
		return out;
	}
	
	/**
	 * 根据作品id获取作品详细信息
	 */
	public OutputObject getWorksInfoById(String worksId) {
		OutputObject out = new OutputObject();
		List imagesList = new ArrayList();
		Issue issue = wechatIssueDao.getWorksInfoById(worksId);
		if (StringUtil.isNotBlank(issue.getOneimagepath())) {
			imagesList.add(issue.getOneimagepath());
		}
		if (StringUtil.isNotBlank(issue.getTwoimagepath())) {
			imagesList.add(issue.getTwoimagepath());
		}
		if (StringUtil.isNotBlank(issue.getThreeimagepath())) {
			imagesList.add(issue.getThreeimagepath());
		}
		if (StringUtil.isNotBlank(issue.getFourimagepath())) {
			imagesList.add(issue.getFourimagepath());
		}
		out.setReturnList(imagesList);
		out.setObject(issue);
		return out;
	}
	
	/**
	 * 删除作品
	 * @param userId
	 * @param worksId
	 * @return
	 */
	public OutputObject deleteIssue(String userId, String worksId) {
		OutputObject out = new OutputObject();
		int i = wechatIssueDao.deleteWorks(userId, worksId);
	    if (1 == i) {
	        out.setReturnCode("0000");
	        out.setReturnMessage("作品已删除");
	        } 
		return out;
	}

}
