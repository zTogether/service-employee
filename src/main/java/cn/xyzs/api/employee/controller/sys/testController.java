package cn.xyzs.api.employee.controller.sys;

import cn.xyzs.api.employee.service.flow.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/sys")
public class testController {

    @Resource
    private TestService testService;
    @GetMapping("index")
    public String index(){
        testService.add1("1233456");
        testService.flow1("215C802EACE048108F8E3128AB5A941C","6F45191BBB79479BA5FFFBCFDE8FE714",true);
        return "Hello";
    }
}
