package cn.xyzs.api.employee.bean;

import java.io.Serializable;

/**
 * Ajax响应体
 * @author zhou
 */
public class AjaxResponseBody implements Serializable{

    private String status;
    private String msg;
    private Object result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
