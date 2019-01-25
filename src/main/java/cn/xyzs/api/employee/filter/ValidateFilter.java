package cn.xyzs.api.employee.filter;

import cn.xyzs.api.employee.bean.AjaxResponseBody;
import cn.xyzs.api.employee.utils.XySqlUtils;
import com.alibaba.fastjson.JSON;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Author: zhouchao
 * @Date: 2019/1/14 0014 15:11
 */
@WebFilter(filterName = "validateFilter",urlPatterns = {"/*"})
public class ValidateFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.err.println("开始执行过滤====================》");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String origin = req.getHeader("Origin");
        resp.setHeader("Access-Control-Allow-Origin", origin);
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        String version = req.getParameter("version");
        if(version==null||"".equals(version)){
            resp.getWriter().write(JSON.toJSONString("必须传入版本号"));
        }else{
            //版本号校验
            String newVersion = null;
            try {
                List<LinkedHashMap<String, Object>> versions = XySqlUtils.createSql("SELECT v.VERSION_CODE from XY_SYS_APP_VERSION v\n" +
                        "WHERE VERSION_ID = (\n" +
                        "\tSELECT MAX(VERSION_ID) from XY_SYS_APP_VERSION\n" +
                        ")",null);
                newVersion = versions.get(0).get("VERSION_CODE").toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(newVersion.equals(version)){
                filterChain.doFilter(servletRequest, servletResponse);
            }else{
                AjaxResponseBody responseBody = new AjaxResponseBody();
                responseBody.setStatus("99");
                responseBody.setMsg("当前不是最新版本,需要升级");
                resp.getWriter().write(JSON.toJSONString(responseBody));
            }
        }

    }
    @Override
    public void destroy() {

    }
}
