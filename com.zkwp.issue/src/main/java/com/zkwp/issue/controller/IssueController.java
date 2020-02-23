package com.zkwp.issue.controller;

import com.zkwp.system.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @auther zhangkun
 * @date 2020/2/22 9:37
 **/
@Controller
@RequestMapping("/issueCenter")
public class IssueController {



    @RequestMapping("/issueIndex")
    public String issueCenter(Model model, @Param("username")String username){

        return "issueIndex";
    }

}
