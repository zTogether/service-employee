package cn.xyzs.api.employee.entity.po.sys;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @Author: zhouchao
 * @Date: 2019/1/12 0012 10:18
 */
@Table(name="XY_SYS_USER_FUC")
@Data
public class XySysUserFuc {
    @Column(name = "USER_ID")
    private String userId;
    @Column(name = "COMPO_ID")
    private String compoId;
    @Column(name = "OP_ID")
    private String opId;
}
