package com.zkwp.consumer.service.system;

import com.zkwp.api.bean.BizSightHistory;
import com.zkwp.api.utils.CommonResult;
import com.zkwp.api.utils.StringUtil;
import com.zkwp.consumer.feign.SystemFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @auther zhangkun
 * @date 2020/4/15 17:31
 **/
@Service
public class HistoryService {

    @Autowired
    SystemFeignService systemFeignService;

    public CommonResult addHistoryService(Map params){
        BizSightHistory bizSightHistory = new BizSightHistory();
        bizSightHistory.setCreatetime(StringUtil.dateToString(new Date()));
        bizSightHistory.setDelflag("0");
        bizSightHistory.setUserId(StringUtil.objToString(params.get("userid")));
        bizSightHistory.setIssueId(StringUtil.objToString(params.get("issueid")));
        return systemFeignService.addHistoryService(bizSightHistory);
    }
}
