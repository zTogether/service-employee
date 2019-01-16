package cn.xyzs.api.employee.entity.po.sys;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "XY_SYS_WORK_ADETAIL")
public class XySysWorkAdetail {

    //主键id
    @Column(name = "WAD_ID")
    private String wadId;

    //
    @Column(name = "APPLY_ID")
    private String applyId;

    //当前节点id
    @Column(name = "NODE_ID")
    private String nodeId;

    //操作人ID
    @Column(name = "XY_USER_ID")
    private String xyUserId;

    //0-提交，1-通过，2-驳回(返回给上一级)，3-拒绝
    @Column(name = "WAD_OPERATION")
    private String wadOperation;

    //备注
    @Column(name = "WAD_REMARK")
    private String wadRemark;

    //添加时间
    @Column(name = "WAD_ADDTIME")
    private String wadAddtime;

    //是否有效
    @Column(name = "WAD_ISDEL")
    private String wadIsdel;
}
