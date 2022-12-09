package org.yzr.config;

import com.alibaba.fastjson.JSON;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/templates")
public class LoginCheckFilter implements Filter {

      public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

      @Override
      public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;

            //获取本次请求地址
            String requestURI = request.getRequestURI();
            //配置不需要拦截的地址
            String[] url = new String[]{"/login", "/apps"};
            if (requestURI.equals("/login")||requestURI.equals("/apps")) {
                  //当浏览器跳转到以login.jsp或DoLogin?action=login结尾时直接进行下一步，而不再进行
                  //过滤验证，即当跳转登陆界面或处理登陆的servlet时不进行过滤拦截
                  filterChain.doFilter(request, response);
                  return;
            }

            //比较
            boolean check = this.check(url, requestURI);
            if (check) {
                  filterChain.doFilter(servletRequest, servletResponse);
                  return;
            }

            if (request.getSession().getAttribute("user") != null) {
                  filterChain.doFilter(servletRequest, servletResponse);
                  return;
            } else {
                  response.sendRedirect("/login");
            }
            response.getWriter().write(JSON.toJSONString("333333"));
            return;
      }

      public boolean check(String[] urls, String requestURI) {
            for (String url : urls) {
                  if (PATH_MATCHER.match(url, requestURI)) {
                        return true;
                  }
            }
            return false;
      }
}