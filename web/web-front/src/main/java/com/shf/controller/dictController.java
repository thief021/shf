package com.shf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shf.entity.Dict;
import com.shf.result.Result;
import com.shf.service.DictService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/dict")
@RestController
public class dictController {
    @Reference
    DictService dictService;
//    查询dict的所有信息
    @RequestMapping("/findListByDictCode/{dictCode}")
    public List<Dict> findListByDictCode(@PathVariable("dictCode")String dictCode){
        List<Dict> listByDictCode = dictService.findListByDictCode(dictCode);
        return listByDictCode;

    }
    //查询dict下区域的信息
    @RequestMapping("/findListByParentId/{id}")
    public List<Dict> findListByParentId(@PathVariable("id")Long aerTd){
        List<Dict> listByParentId = dictService.findListByParentId(aerTd);
        return listByParentId;
    }

}
