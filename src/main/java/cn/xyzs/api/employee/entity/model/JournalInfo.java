package cn.xyzs.api.employee.entity.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

/**
 * @author zhou
 */
@Component
@Table(name = "XY_SYS_JOURNAL_LOG")
public class JournalInfo {
    /**日志id*/
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="sequence")
    @SequenceGenerator(name="sequence",sequenceName="seq_sys_log",initialValue=1,allocationSize=1)
    private Integer logId;
    /**用户id*/
    @Column(name = "USER_ID")
    private String uid;
    /**模块类型*/
    @Column(name = "MODULAR_TYPE")
    private String modularType;
    /**操作类别*/
    @Column(name = "OPERATION_TYPE")
    private String operationType;
    /**操作时间*/
    @Column(name = "OPERATION_TIME")
    private Date operationTime;

    public Integer getId() {
        return logId;
    }

    public void setId(Integer logId) {
        this.logId = logId;
    }
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getModularType() {
        return modularType;
    }

    public void setModularType(String modularType) {
        this.modularType = modularType;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }
}
