package cn.xyzs.api.employee.entity.po.sys;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * 用户类
 */
@Table(name="XY_SYS_USER")
@Data
public class XySysUser {
    /**
     * 用户唯一标识ID
     */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select sys_guid() from dual")
    private String userId;
    /**
     * 员工工号
     */

    @Column(name="USER_CODE")
    private String userCode;
    /**
     * 用户真实名
     */
    private String userName;
    /**
     * 用户电话
     */
    @Column(name = "USER_TEL")
    private String userTel;
    /**
     * 用户密码
     */
    @Column(name = "PASSWORD")
    private String password;
    /**
     * 生日
     */
    @Column(name = "USER_BTHD")
    private String userBthd;
    /**
     * 性别
     */
    @Column(name = "USER_SEX")
    private String userSex;
    /**
     * 是否启用
     */
    @Column(name = "IS_USED")
    private String isUsed;
    /**
     * 身份证号
     */
    @Column(name = "ID_CARD")
    private String idCard;

    @Column(name = "USER_MARK1")
    private String userMark1;
    @Column(name = "USER_MARK2")
    private String userMark2;
    @Column(name = "USER_MARK3")
    private String userMark3;
    /**
     * 是否需要改密码
     */
    @Column(name = "MUST_CHANGE")
    private String mustChange;

    /**
     * 一个用户具有多个角色
     */
    @Transient
    private List<String> roleList;

    /**
     * 一个用户具有独立多个权限
     */
    @Transient
    private List<String> compoList;
}