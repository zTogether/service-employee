package cn.xyzs.api.employee.enums;


/**
 * http 枚举放回状态码和Msg
 * @author zhou
 */

public enum ResultEnum {

    UNKONW_ERROR(-1,"未知错误"),
    OK(0, "OK"),
    IS404_ERROR(400,"404错误"),
    IS500_ERROR(500,"500错误"),
    JOURNAL_LOG_ERROR(320,"日志注解错误"),
    NO_ALLOW(201,"缺少操作权限");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {

        return code;
    }

    public String getMsg() {
        return msg;
    }
}
