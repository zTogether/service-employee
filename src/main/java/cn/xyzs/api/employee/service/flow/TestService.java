package cn.xyzs.api.employee.service.flow;

import org.springframework.stereotype.Service;

/**
 * @Author: zhouchao
 * @Date: 2019/1/14 0014 16:17
 */
public interface TestService {

    public void add1(String uid);
    public void flow1(String applyId,String nodeId,boolean flag);
}
