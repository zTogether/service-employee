package cn.xyzs.api.employee.exception;

import cn.xyzs.api.employee.entity.model.Result;
import cn.xyzs.api.employee.enums.ResultEnum;
import cn.xyzs.api.employee.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 全局异常捕获
 * @author zhou
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 系统异常处理，比如：404,500
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result defaultErrorHandler(Exception e) throws Exception {
        logger.error("错误信息", e);
        ResultEnum resultEnum;
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            logger.error("404错误");
            resultEnum = ResultEnum.IS404_ERROR;
            return ResultUtils.error(resultEnum);
        } else if (e instanceof CustomException)  {
            logger.error("500错误");
            CustomException customException = (CustomException)e;
            return ResultUtils.error(customException.getCode(),customException.getMessage());
        }else{
            logger.error("500错误");
            return ResultUtils.error(-1,e.getLocalizedMessage());
        }
    }
}
