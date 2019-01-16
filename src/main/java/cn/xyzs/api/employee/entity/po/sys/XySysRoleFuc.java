package cn.xyzs.api.employee.entity.po.sys;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "XY_SYS_ROLE_FUC")
public class XySysRoleFuc {
    //角色编号
    @Setter
    @Getter
    @Column(name = "ROLE_ID")
    private String roleId;
    //菜单ID
    @Setter
    @Getter
    @Column(name = "COMPO_ID")
    private String compoId;
    //操作ID
    @Setter
    @Getter
    @Column(name = "OP_ID")
    private String opId;
}
