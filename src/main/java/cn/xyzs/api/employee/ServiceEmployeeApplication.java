package cn.xyzs.api.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

@SpringBootApplication
@EnableCaching
public class ServiceEmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceEmployeeApplication.class, args);
	}
    @Bean
    public MapperScannerConfigurer create(){
        MapperScannerConfigurer config = new MapperScannerConfigurer();
        //设置mapper文件的包
        config.setBasePackage("cn.xyzs.api.employee.mapper");
        //固定配置
        Properties p = new Properties();
        p.setProperty("mappers","tk.mybatis.mapper.common.Mapper");
        config.setProperties(p);
        return config;
    }
}
