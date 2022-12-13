package com.shf.controll;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.shf.controller.BaseController;
import com.shf.entity.*;
import com.shf.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/house")
public class houseController extends BaseController {
    @Reference
    HouseService houseService;
    @Reference
    CommunityService communityService;
    @Reference
    DictService dictService;
    @Reference
    HouseImageService houseImageService;
    @Reference
    HouseBrokerService houseBrokerService;
    @Reference
    HouseUserService houseUserService;
    @RequestMapping("/house")
    public String index(Map map, HttpServletRequest request){
        Map<String, Object> filters = getFilters(request);
        map.put("filters",filters);
        PageInfo<House> page = houseService.findPage(filters);
        map.put("page",page);
        map.put("communityList",communityService.findAll());
        map.put("houseTypeList",dictService.findListByDictCode("houseType"));
        map.put("floorList",dictService.findListByDictCode("floor"));
        map.put("buildStructureList",dictService.findListByDictCode("buildStructure"));
        map.put("directionList",dictService.findListByDictCode("direction"));
        map.put("decorationList",dictService.findListByDictCode("decoration"));
        map.put("houseUseList",dictService.findListByDictCode("houseUse"));


        return "house/index";
    }
    @RequestMapping("/create")
    public String carete(Map map){
        map.put("communityList",communityService.findAll());
        map.put("houseTypeList",dictService.findListByDictCode("houseType"));
        map.put("floorList",dictService.findListByDictCode("floor"));
        map.put("buildStructureList",dictService.findListByDictCode("buildStructure"));
        map.put("directionList",dictService.findListByDictCode("direction"));
        map.put("decorationList",dictService.findListByDictCode("decoration"));
        map.put("houseUseList",dictService.findListByDictCode("houseUse"));

        return "house/create";
    }
    @RequestMapping("/save")
    public String save(House house){
        houseService.insert(house);

        return "common/successPage";
    }
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id")Long id, Map map){
        House house = houseService.getById(id);
        map.put("house",house);
        map.put("communityList",communityService.findAll());
        map.put("houseTypeList",dictService.findListByDictCode("houseType"));
        map.put("floorList",dictService.findListByDictCode("floor"));
        map.put("buildStructureList",dictService.findListByDictCode("buildStructure"));
        map.put("directionList",dictService.findListByDictCode("direction"));
        map.put("decorationList",dictService.findListByDictCode("decoration"));
        map.put("houseUseList",dictService.findListByDictCode("houseUse"));
        return "house/edit";
    }
    @RequestMapping("/update")
    public  String update(House house  ){
        houseService.update(house);
        return "common/successPage";

    }
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id")Long id){
        houseService.delete(id);
        return "redirect:/house/house";

    }
    @RequestMapping("/publish/{id}/{status}")
    public String publish(@PathVariable("id")Long id,@PathVariable("status") Integer status){
        houseService.publish(id,status);

        return "redirect:/house/house";
    }
    @RequestMapping("/{houseId}")
    public String goIndex(@PathVariable("houseId")long id,Map map) {
//根据ID查询到房屋的信息@PathVariable("houseId")long id
        House house = houseService.getById(id);
        //根据房屋的得到communityid然后查询到community的信息
        Community community = communityService.getById(house.getCommunityId());
//      查血houseBroker的数据名字要和html名字一致
        List<HouseBroker> houseBrokerList = houseBrokerService.findListByHouseId(id);
        //查询两个种类的图片
        List<HouseImage> houseImage1List = houseImageService.findList(id, 1);
        List<HouseImage> houseImage2List = houseImageService.findList(id, 2);
//        查询房屋使用者的信息
        List<HouseUser> houseUserList = houseUserService.findListByHouseId(id);


        //放入到request域中
        map.put("community",community);

        map.put("house",house );
        map.put("houseBrokerList",houseBrokerList);
        map.put("houseImage1List",houseImage1List);
        map.put("houseImage2List",houseImage2List);
        map.put("houseUserList",houseUserList);
        return "house/show";


    }
}
