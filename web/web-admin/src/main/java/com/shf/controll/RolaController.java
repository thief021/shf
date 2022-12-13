package com.shf.controll;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.shf.controller.BaseController;
import com.shf.entity.Role;
import com.shf.service.PermissionService;
import com.shf.service.RolaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RolaController extends BaseController {
    public static final String PAGE_SUCCESS = "common/successPage";
    @Reference
    public RolaService rolaService;
    @Reference
    PermissionService permissionService;

    @RequestMapping()
    public String findAll(ModelMap map, HttpServletRequest request) {
        //查询所有所有的请求
        Map<String,Object> filters = getFilters(request);
//        找到页面所在
        PageInfo<Role> page = rolaService.findPage(filters);
//    放到request的请求域中
        map.addAttribute("page", page);
        map.addAttribute("filters", filters);
        return "rola/index";


    }

    @RequestMapping("/create")
    public String goAddcreate() {
        return "rola/create";
    }

    @RequestMapping("/save")
    public String Insert(Role role) {
        rolaService.insert(role);
        return PAGE_SUCCESS;


    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        rolaService.delete(id);
        return "redirect:/rola";

    }

    @RequestMapping("/edit/{id}")
    public String goEdit(@PathVariable("id") Long id, Map maps) {
//        根据id得到role对象;
        Role role = rolaService.getById(id);
//         把得到的role对象修改到数据库中
        maps.put("role", role);
        return "rola/edit";


    }

    @RequestMapping("/update")
    public String goUpdate(Role role) {
        rolaService.update(role);
        return PAGE_SUCCESS;
    }

    /**
     * 封装页面提交的分页参数及搜索条件
     *
     * @param
     * @return
     */
//    private Map<String, Object> getFilters(HttpServletRequest request) {
//        Enumeration<String> paramNames = request.getParameterNames();
//        Map<String, Object> filters = new TreeMap();
//        while (paramNames != null && paramNames.hasMoreElements()) {
//            String paramName = (String) paramNames.nextElement();
//            String[] values = request.getParameterValues(paramName);
//            if (values != null && values.length != 0) {
//                if (values.length > 1) {
//                    filters.put(paramName, values);
//                } else {
//                    filters.put(paramName, values[0]);
//                }
//            }
//        }
//        if (!filters.containsKey("pageNum")) {
//            filters.put("pageNum", 1);
//        }
//        if (!filters.containsKey("pageSize")) {
//            filters.put("pageSize", 10);
//        }
//
//        return filters;
//    }
    //给用户增加权限
    @RequestMapping("/assignShow/{id}")
    public String assignShow(@PathVariable("id")Long id,Map map){
        //将id放到请求域中
        map.put("id",id);
        //调用
        List<Map<String,Object>> zNodes=permissionService.findListbyLongId(id);
        map.put("zNodes", zNodes);

      return "rola/assginShow";
    }
    //保存用户权限
    @RequestMapping("/assignPermission")
    public String assignPermission(@RequestParam("roleId")Long roleId,@RequestParam("permissionIds")Long[] permissionIds){
        permissionService.assignPermission(roleId,permissionIds);
        return "common/successPage";
    }
}
