package cn.xyzs.api.employee.aspect;

import cn.xyzs.api.employee.entity.po.sys.XySysWorkAdetail;
import cn.xyzs.api.employee.entity.po.sys.XySysWorkNode;
import cn.xyzs.api.employee.exception.CustomException;
import cn.xyzs.api.employee.manager.XyWorkManager;
import cn.xyzs.api.employee.mapper.sys.flow.XySysWorkAdetailMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * AOP 添加下一个节点(参数顺序,applyId>nodeId>flag>其他)
 * @Author: zhouchao
 * @Date: 2019/1/16 0016 12:42
 */
@Component
@Aspect
public class FlowAspect {

    @Resource
    private XyWorkManager workManager;
    @Resource
    private XySysWorkAdetailMapper workAdetailMapper;

    /**service层切面*/
    private final String POINT_CUT = "execution(* cn.xyzs.api.employee.service..flow*(..))";

    @Pointcut(POINT_CUT)
    private void pointcut(){}

    /**
     * 后置最终通知（目标方法只要执行完了就会执行后置通知方法）
     * 日志管理
     * @param joinPoint
     */
    @After(value = "pointcut()")
    @Transactional
    public void doAfterAdvice(JoinPoint joinPoint) throws CustomException {
        Signature signature = joinPoint.getSignature();
        Object[] objs = joinPoint.getArgs();
        String applyId = objs[0].toString();
        String nodeId = objs[1].toString();
        Boolean flag = (boolean)objs[2];
        //获取判断下一个节点
        XySysWorkNode node =  workManager.getNextNode(nodeId,flag);
        if (node!=null){
            //添加下一个节点
            XySysWorkAdetail workAdetail = new XySysWorkAdetail();
            workAdetail.setApplyId(applyId);
            workAdetail.setNodeId(node.getNodeId());
            workAdetailMapper.insertSelective(workAdetail);
        }
    }
}
