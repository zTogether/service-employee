package cn.xyzs.api.employee.config;

import cn.xyzs.api.employee.filter.ValidateFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author: zhouchao
 * @Date: 2019/1/14 0014 15:15
 */
@Configuration
public class WebConfiguration {

    @Bean
    public FilterRegistrationBean registerFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new ValidateFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("validateFilter");
        registration.setOrder(1);
        return registration;
    }
}
