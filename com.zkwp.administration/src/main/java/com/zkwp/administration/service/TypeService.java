package com.zkwp.administration.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zkwp.administration.dao.TypeDao;
import com.zkwp.api.bean.Type;
import com.zkwp.api.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @auther zhangkun
 * @date 2020/3/29 20:43
 **/
@Service
public class TypeService {

    @Autowired
    TypeDao typeDao;

    public PageInfo<Type> queryTypes(Integer pageNumber, Integer pageSize){
        PageHelper.startPage(pageNumber,pageSize);
        List<Type> types = typeDao.queryTypes(null);
        // 5表示设置连续显示的页号数目 1 2 3 4 5
        return new PageInfo<>(types,5);
    }

    public int insertType(Type type){
        type.setCreatetime(StringUtil.dateToString(new Date()));
        return typeDao.insertType(type);
    }

    public int deleteType(Integer id){
        return typeDao.deleteType(id);
    }
    public int deleteTypes(String ids){
        String[] id_strs = ids.split("-");
        List<Integer> idList = new ArrayList();
        for (String id_str : id_strs) {
            idList.add(Integer.parseInt(id_str));
        }
        return typeDao.deleteTypes(idList);
    }

    public int updateType(Type type){
        return typeDao.updateType(type);
    }

    public Type getTypeById(Integer id){
        return  typeDao.getTypeById(id);
    }

    public Boolean checkNameExit(String name){
        if(typeDao.checkNameExit(name)==1) return true;
        else return false;
    }

    public List<Type> getTypeListByPCode(String code){
        return typeDao.getTypeListByPCode(code);
    }

    public Type getTypeByCode(String code){
        return typeDao.getTypeByCode(code);
    }
}
