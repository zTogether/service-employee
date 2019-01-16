package cn.xyzs.api.employee.entity.po.sys;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "XY_SYS_WORK_GROUP")
public class XySysWorkGroup {

    //主键
    @Column(name = "GROUP_ID")
    private String groupId;

    //群组名称
    @Column(name = "GROUP_NAME")
    private String groupName;

    //群组描述
    @Column(name = "GROUP_DESC")
    private String groupDesc;
}
