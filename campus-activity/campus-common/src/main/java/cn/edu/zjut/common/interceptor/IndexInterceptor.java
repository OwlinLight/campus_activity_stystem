package cn.edu.zjut.common.interceptor;


import cn.edu.zjut.common.service.CollegeService;
import cn.edu.zjut.common.service.UserService;
import cn.edu.zjut.common.wrapper.RequestWrapper;
import com.alibaba.fastjson.JSON;


import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class IndexInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private CollegeService collegeService;

    @Autowired
    private UserService userService;
    public IndexInterceptor() {}

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RequestWrapper myRequestWrapper = new RequestWrapper((HttpServletRequest) request);
        String body = myRequestWrapper.getBody();
//        System.out.println("我是拦截器："+body);

        // do something
        JSONObject obj=JSON.parseObject(body);
        Long cnt = collegeService.getCollegeId(obj.getString("collegeName"));
        Long cnt1 = userService.askIdByName(obj.getString("directorName"));
        System.out.println(cnt+" "+ cnt1+ " "+obj.getString("directorName"));
        if(cnt == null || cnt1 == null){
            return false;
        }
        return true;

    }
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
