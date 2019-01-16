package cn.xyzs.api.employee.entity.po.sys;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name = "XY_SYS_COMPO")
@Data
public class XySysCompo {
    /**
     * 功能ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select sys_guid() from dual")
    private String compoId;
    /**
     * 菜单链接
     */
    @Column(name = "COMPO_CODE")
    private String compoCode;
    /**
     * 菜单名
     */
    @Column(name = "COMPO_NAME")
    private String compoName;

}
