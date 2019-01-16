package cn.xyzs.api.employee.entity.po.sys;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "XY_SYS_USER_ROLE")
@Data
public class XySysUserRole {
    /**
     * 用户角色ID
     */
    @Column(name = "UR_ID")
    private String urId;
    /**
     * 员工ID
     */
    @Column(name = "USER_ID")
    private String userd;
    /**
     * 角色ID
     */
    @Column(name = "ROLE_ID")
    private String roled;

}
