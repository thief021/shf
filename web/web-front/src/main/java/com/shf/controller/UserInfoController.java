package com.shf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shf.entity.UserInfo;
import com.shf.result.Result;
import com.shf.result.ResultCodeEnum;
import com.shf.service.UserInfoService;
import com.shf.util.MD5;
import com.shf.vo.HouseVo;
import com.shf.vo.LoginVo;
import com.shf.vo.RegisterVo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/userInfo")
public class UserInfoController extends BaseController{
    @Reference
    UserInfoService userInfoService;
    @RequestMapping("/sendCode/{phone}")
    public Result sendCode(@PathVariable("phone")String phone, HttpServletRequest httpServletRequest){
//        有一个phone的字符串,有一个请求参数,我们需要做的事,是根据电话号码发送一个验证码然后验证验证码是否正确.
        String s = UUID.randomUUID().toString();
        System.out.println(s);
        //得到输入的验证码
        httpServletRequest.getSession().setAttribute("s",s);
        return Result.ok(s);

    }
    //注册页面
    @RequestMapping("/register")
    public Result register(@RequestBody RegisterVo registerVo, HttpSession httpSession){
//        需要验证验证码,需要得到验证码(使用session可以获得验证码)
//        根据传过来的对象得到对象的属性
        String code = registerVo.getCode();
        String nickName = registerVo.getNickName();
        String encrypt = MD5.encrypt(registerVo.getPassword());
        String phone = registerVo.getPhone();
//严空
        if(code==null||nickName==null||encrypt==null){
            return Result.build(null, ResultCodeEnum.PARAM_ERROR);
        }

        String s =(String) httpSession.getAttribute("s");
        if(!code.equals(s)){
            return Result.build(null,ResultCodeEnum.CODE_ERROR);
        }
        //判断数据库中是否有存在的phone
        UserInfo userInfo= userInfoService.getIdByPhone(phone);
        if(null !=userInfo){
            return Result.build(null,ResultCodeEnum.PHONE_REGISTER_ERROR);
        }
        //把数据写进去,因为是一个新的数据所以得新建,然后设置数据
        UserInfo userInfo1 = new UserInfo();
        userInfo1.setNickName(nickName);
        userInfo1.setPassword(encrypt);
        userInfo1.setPhone(phone);
        userInfo1.setStatus(1);
        //保存到数据库之中
        userInfoService.insert(userInfo1);
        return Result.ok();
    }
    @RequestMapping("/login")
    public Result login(@RequestBody LoginVo loginVo,HttpSession session){
        //首先根据传过来的对象判断数据是否为空
        String password = loginVo.getPassword();
        String phone = loginVo.getPhone();
        //判断是否为空
        if(StringUtils.isEmpty(password)||StringUtils.isEmpty(phone)){
            return Result.build(null,ResultCodeEnum.PARAM_ERROR);
        }
        //判断手机号是否正确
        UserInfo idByPhone = userInfoService.getIdByPhone(phone);
        if(null==idByPhone){
            return Result.build(null,ResultCodeEnum.ACCOUNT_ERROR);
        }
        //判断验证码是否正确
        if(!MD5.encrypt(password).equals(idByPhone.getPassword())){
            return Result.build(null,ResultCodeEnum.CODE_ERROR);
        }
        //把数据存放到session中
        session.setAttribute("user",idByPhone);
//        返回一个map
        Map map = new HashMap();
        map.put("nickName",idByPhone.getNickName());
        return Result.ok(map);


    }
    @RequestMapping("/logout")
    public Result logout(HttpSession session){
//        退出页面的主要逻辑就是移除seesion中的使用者的信息
        session.removeAttribute("user");
        return Result.ok();
    }
}
