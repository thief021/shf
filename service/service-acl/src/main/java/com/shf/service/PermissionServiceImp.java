package com.shf.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.shf.dao.BaseDao;
import com.shf.dao.PermissionDao;
import com.shf.dao.RolePermissionDao;
import com.shf.entity.Permission;
import com.shf.helder.PermissionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = PermissionService.class)
@Transactional
public class PermissionServiceImp extends BaseSeriviceImp<Permission> implements PermissionService{
    @Autowired
    PermissionDao permissionDao;
    @Autowired
    RolePermissionDao rolePermissionDao;
    @Override
    protected BaseDao<Permission> getEntityDao() {
        return this.permissionDao;
    }

    @Override
    public List<Map<String, Object>> findListbyLongId(Long id) {
        //需要做的事,查询所有的权限
        List<Permission> permissionList= permissionDao.findAll();
        //获取用户的权限
        List<Long> permissionIds= rolePermissionDao.findListByroleId(id);
//        创建返回的list
        List<Map<String,Object>> list=new ArrayList<>();
        //遍历
        for (Permission permission:permissionList) {
//            要按固定的格式来存储数据
            HashMap<String, Object> objectObjectHashMap = new HashMap<>();
            objectObjectHashMap.put("id",permission.getId());
            objectObjectHashMap.put("pId",permission.getParentId());
            objectObjectHashMap.put("name",permission.getName());
            //判断是否存在这个权限
            if(permissionIds.contains(permission.getId())){
                objectObjectHashMap.put("checked",true);

            }
            list.add(objectObjectHashMap);
        }
        return list ;
    }
//保存和删除选中的节点数据
    @Override
    public void assignPermission(Long roleId, Long[] permissionIds) {
        //先删除后添加
        rolePermissionDao.deleteByroleId(roleId);
        for (Long permissionId:permissionIds){
            if(permissionId!=null){
                //写入一个新的rolepermission对象
                rolePermissionDao.insetByroleIdandPId(roleId,permissionId);
            }
        }

    }
//动态显示权限
    @Override
    public List<Permission> findpermissionByAdminId(Long id) {
        List<Permission> permissionList=null;
       if(id==1){
           permissionList = permissionDao.findAll();
       }else{
          permissionList=  permissionDao.findpermissionByAdminId(id);


       }
        List<Permission>  treelist= PermissionHelper.bulid(permissionList);
        return treelist;
    }
}
