package com.shf.controll;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shf.entity.HouseUser;
import com.shf.service.HouseService;
import com.shf.service.HouseUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/houseUser")
public class houseUserController {
    @Reference
    HouseUserService houseUserService;
    @RequestMapping("/create")
    public String goCreate(@RequestParam("houseId")Long id, Map map){
        map.put("houseId",id);
        return "houseUser/create";

    }
    @RequestMapping("/save")
    public String Save(HouseUser houseUser){
        houseUserService.insert(houseUser);
        return "common/successPage";
    }
    @RequestMapping("/edit/{id}")
    public String goEdit(@PathVariable("id")Long id,Map map){
//        根据id得到houseUse信息
        HouseUser houseUser = houseUserService.getById(id);
//        放到请求域中
        map.put("houseUser",houseUser);
        return "houseUser/edit";


    }
    @RequestMapping("/update")
    public  String update(HouseUser houseUser){
        houseUserService.update(houseUser);
        return "common/successPage";
    }
    @RequestMapping("/delete/{houseId}/{houseUserId}")
    public  String delete(@PathVariable("houseId")Long houseId,@PathVariable("houseUserId")Long houseUseId){
        houseUserService.delete(houseUseId);
        return "redirect:/house/"+houseId;
    }

}
