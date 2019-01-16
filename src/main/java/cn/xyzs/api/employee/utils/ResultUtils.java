package cn.xyzs.api.employee.utils;


import cn.xyzs.api.employee.entity.model.Result;
import cn.xyzs.api.employee.enums.ResultEnum;


/**
 * 请求返回最外层
 * @author zhou
 */
public class ResultUtils {

    /**
     * Object返回http回调成功
     * @param obj
     * @return
     */
    public static Result success(Object obj){
        Result result = new Result();
        result.setCode(ResultEnum.OK.getCode());
        result.setMsg(ResultEnum.OK.getMsg());
        result.setData(obj);
        return result;
    }
    /**
     * 枚举返回http回调成功
     * @param resultEnum
     * @return
     */
    public static Result success(ResultEnum resultEnum){
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        result.setData(null);
        return result;
    }
    /**
     * 枚举返回错误信息
     * @param resultEnum
     * @return
     */
    public static Result error(ResultEnum resultEnum){
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        return  result;
    }
    /**
     * 无object返回
     * @return
     */
    public  static Result success(){
        return success(null);
    }

    /**
     * http回调错误
     * @param code
     * @param msg
     * @return
     */
    public static Result error(Integer code, String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return  result;
    }

    /**
     * 分页返回数据
     * @param data
     * @param total
     */
    public static Result pageResult(Object data,long total) {
        Result result = new Result();
        result.setCode(1);
        result.setData(data);
        result.setTotal(total);
        return result;
    }
}
