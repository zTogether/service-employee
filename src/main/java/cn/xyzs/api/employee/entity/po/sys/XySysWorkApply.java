package cn.xyzs.api.employee.entity.po.sys;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "XY_SYS_WORK_APPLY")
public class XySysWorkApply {

    //
    @Column(name = "APPLY_ID")
    private String applyId;

    //
    @Column(name = "APPLY_USERID")
    private String applyUserid;

    //工作流id
    @Column(name = "ACT_ID")
    private String actId;

    //申请主题
    @Column(name = "APPLY_TITLE")
    private String applyTitle;

    //申请内容
    @Column(name = "APPLY_CONTENT")
    private String applyContent;

    //当前工作流状态（1-已提交,2-运行中,3-结束）
    @Column(name = "APPLY_STATE")
    private String applyState;

    //第一个节点提交给指定的人审批
    @Column(name = "APPLY_TARGETUSERID")
    private String applyTargetuserid;

    //优先级
    @Column(name = "APPLY_PRIORITY")
    private String applyPriority;

    //添加时间
    @Column(name = "APPLY_ADDTIME")
    private String applyAddtime;

    //是否删除
    @Column(name = "APPLY_ISDEL")
    private String applyIsdel;
}
