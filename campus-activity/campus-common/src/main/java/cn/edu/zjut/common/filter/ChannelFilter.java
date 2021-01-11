package cn.edu.zjut.common.filter;



import cn.edu.zjut.common.wrapper.RequestWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ChannelFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
    }
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ServletRequest requestWrapper = null;
        System.out.println("我是过滤器");
        if(request instanceof HttpServletRequest) {
            requestWrapper = new RequestWrapper((HttpServletRequest) request);
        }
        if(requestWrapper == null) {
            chain.doFilter(request, response);
        } else {
            chain.doFilter(requestWrapper, response);
        }
    }
    public void destroy() {

    }
}
