package com.shf.intercept;

import com.shf.entity.UserInfo;
import com.shf.result.Result;
import com.shf.result.ResultCodeEnum;
import com.shf.util.WebUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@Aspect
public class UserFollowIntercept {
    @Before("execution( * com.shf.controller.UserFollowController.*(..))")
    public Boolean before(HttpSession session, HttpServletRequest request, HttpServletResponse response){
        //感受session得到一个useinfo对象
        UserInfo userInfo =(UserInfo) request.getSession().getAttribute("use");
//        判断对象是否为空,如果为空为未登录
        if(null==userInfo){
            Result<String> result = Result.build("还没有登录", ResultCodeEnum.LOGIN_AUTH);
            WebUtil.writeJSON(response,result);
            return false;
        }
        return true;

    }
}
