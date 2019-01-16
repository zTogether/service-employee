package cn.xyzs.api.employee.exception;


import cn.xyzs.api.employee.enums.ResultEnum;

/**
 * 自定义异常类型
 * @author zhou
 */
public class CustomException extends Exception {
    
    private Integer code;
    public CustomException(){}
    
    public CustomException(ResultEnum resultEnum) {
        super((resultEnum.getMsg()));
        this.code = resultEnum.getCode();
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}