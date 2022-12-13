package com.shf.controll;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shf.entity.Dict;
import com.shf.result.Result;
import com.shf.service.DictService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RequestMapping("/dict")
@Controller
public class DictController {
    @Reference
    DictService dictService;
    @RequestMapping("/dict")
    public String index(){
        return "dict/index";

    }
    @ResponseBody
    @RequestMapping("/findZnodes")
    public Result findZnodes(@RequestParam(value = "id",defaultValue ="0" )Long id){
        //根据前段传过来的ID查询所有父节点的数据
        List<Map<String ,Object>> znodes=dictService.findZnodes(id);
        //放到request雨中
        return Result.ok(znodes);

    }
//    @ResponseBody("/house")
//    public String findByparentId(long id){
//        return ""
//    }

    @GetMapping(value = "findListByParentId/{parentId}")
    @ResponseBody
    public Result<List<Dict>> findListByParentId(@PathVariable("parentId") Long parentId) {
        List<Dict> list = dictService.findListByParentId(parentId);
        return Result.ok(list);
    }

    /**
     * 根据编码获取子节点数据列表
     * @param dictCode
     * @return
     */
    @GetMapping(value = "findListByDictCode/{dictCode}")
    @ResponseBody
    public Result<List<Dict>> findListByDictCode(@PathVariable("dictCode") String dictCode) {
        List<Dict> list = dictService.findListByDictCode(dictCode);
        return Result.ok(list);
    }

}
