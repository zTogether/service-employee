package cn.xyzs.api.employee.aspect;

import cn.xyzs.api.employee.entity.po.sys.XySysWorkAdetail;
import cn.xyzs.api.employee.entity.po.sys.XySysWorkNode;
import cn.xyzs.api.employee.enums.ResultEnum;
import cn.xyzs.api.employee.exception.CustomException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 权限校验 （方法参数顺序:user_id>role_id>其他）
 * @Author: zhouchao
 * @Date: 2019/1/16 0016 13:13
 */
@Component
@Aspect
public class PermissionAspect {

    /**service层切面*/
    private final String POINT_CUT = "execution(* cn.xyzs.api.employee.service..*(..))";

    @Pointcut(POINT_CUT)
    private void pointcut(){}

    @Before(value = "pointcut()")
    public void doBeforeVaildate(JoinPoint joinPoint) throws CustomException {
        //操作权限校验
        /*
        Object[] objs = joinPoint.getArgs();
        String userId = objs[0].toString();
        String roleId = objs[1].toString();
        throw new CustomException(ResultEnum.NO_ALLOW);
        */
    }
}
