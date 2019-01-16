package cn.xyzs.api.employee.service.flow.impl;

import cn.xyzs.api.employee.service.flow.TestService;
import org.springframework.stereotype.Service;

/**
 * @Author: zhouchao
 * @Date: 2019/1/16 0016 9:29
 */
@Service
public class TestServiceImpl implements TestService {
    @Override
    public void add1(String uid) {
        System.out.println(uid+"正在执行添加方法");
    }

    @Override
    public void flow1(String applyId, String nodeId, boolean flag) {
        int a = 1/0;
        System.out.println("添加下一个节点");
    }
}
