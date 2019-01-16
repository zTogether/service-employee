package cn.xyzs.api.employee.entity.po.sys;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name = "XY_SYS_ROLE")
@Data
public class XySysRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select sys_guid() from dual")
    private String roleId;
    @Column(name = "ROLE_NAME")
    private String roleName;
    @Column(name = "ROLE_TYPE")
    private String roleType;

}
