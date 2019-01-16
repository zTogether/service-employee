package cn.xyzs.api.employee.entity.model;

/**
 * http请求返回的最外层
 * @author zhou
 */
public class Result<T> {
    private Integer code;

    private String msg;

    private T data;
    public long total;
    public long  pageCount;



    public long getPageCount() {

        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    public long getTotal() {

        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" +
                "\"code\":" + code +
                ",\"msg\":\"" + msg + "\"" +
                ", \"data\":" + data +
                ", \"total\":" + total +
                ", \"pageCount\":" + pageCount +
                '}';
    }
}
