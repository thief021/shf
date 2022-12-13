package com.shf.controll;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.shf.controller.BaseController;
import com.shf.entity.Admin;
import com.shf.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.shf.util.QiniuUtils.upload2Qiniu;

@Controller
@RequestMapping("/admin")
public class adminCntroller extends BaseController {
    @Reference
    public AdminService adminService;
    @Reference
    PasswordEncoder passwordEncoder;
    @RequestMapping()
    public String findall(Map map, HttpServletRequest request){
        Map<String, Object> filters = getFilters(request);
        map.put("filters",filters);
        PageInfo<Admin> page = adminService.findPage(filters);
        map.put("page",page);
        return "admin/index";


    }
    @RequestMapping("/uploadShow/{id}")
    public  String goAddImage(@PathVariable("id")Long id,Map map){
        map.put("id",id);
        return "admin/upload";
    }
    @RequestMapping("/upload/{id}")
    public String saveImage(@PathVariable("id")Long id, MultipartFile file){
        try {
            //把文件路径的图片转为二进制数组
            byte[] fileBytes = file.getBytes();
            //给文件设置新的名字
            String ImageName = UUID.randomUUID().toString();
//            把图片上传到数据库
//            这是新增而不是修改数据,如果你根据id查询到数据是数据库里面的数据你即使设置了还是数据库里面的
            Admin admin = new Admin();

            admin.setHeadUrl("http://rm56bq9kk.hn-bkt.clouddn.com"+ImageName);
            admin.setId(id);

            adminService.update(admin);
//            把文件上传到七牛云
            upload2Qiniu(fileBytes,ImageName);


        }
        catch (IOException e){
            e.printStackTrace();
        }
        return "common/successPage";
    }
//去新增页面
    @RequestMapping("/create")
    public String create(){
        return "admin/create";
    }
    //保存用户的数据
    @RequestMapping("/save")
    public String save(Admin admin){
        //把加入的密码用密文加密
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminService.insert(admin);
        return "common/successPage";
    }
}
