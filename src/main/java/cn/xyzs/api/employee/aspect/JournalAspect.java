package cn.xyzs.api.employee.aspect;

import cn.xyzs.api.employee.exception.CustomException;
import cn.xyzs.api.employee.utils.JournalUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 日志切面
 * @author zhou
 */
@Component
@Aspect
public class JournalAspect {
    /**日志输出*/
    private static final Logger logger = LoggerFactory.getLogger(JournalAspect.class);

    /**日志工具类*/
    @Resource
    private JournalUtils aspectJournalUtils;

    /**service层切面*/
    private final String POINT_CUT = "execution(* cn.xyzs.api.employee.service..*(..))";

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
        //通知的签名
        Signature signature = joinPoint.getSignature();
        //包名+类名
        String declaringTypeName = signature.getDeclaringTypeName();
        //1.获取模块类型(当前包名+类名)
        String modulerType = declaringTypeName;
        //2.获取操作类型(方法名开头匹配)
        String mothedName = signature.getName();
        if (!this.checkStr(mothedName)){
            return;
        }
        String opreationType = this.getOperation(mothedName);
        //3.获取操作用户(参数第一个)
        Object[] objs = joinPoint.getArgs();
        String uid = objs[0].toString();
        //3.添加日志
        aspectJournalUtils.addJournalInfo(modulerType, opreationType, uid);
    }

    /**
     * 检测增删改操作
     * @param str
     */
    public boolean checkStr(String str){
        String regEx = "(add|delete|modify)[0-9a-zA-Z_]*";
        boolean b = Pattern.matches(regEx,str);
        return b;
    }

    /**
     * 提取操作
     * @param str
     * @return
     */
    public String getOperation(String str){
        String result = null;
        String regEx = "(add|delete|modify)";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            result = matcher.group(1);
        }
        return result;
    }
}
