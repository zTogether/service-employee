package cn.xyzs.api.employee.mapper.sys.flow;

import cn.xyzs.api.employee.entity.po.sys.XySysWorkGroup;
import cn.xyzs.api.employee.entity.po.sys.XySysWorkNode;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: zhouchao
 * @Date: 2019/1/12 0012 10:46
 */
public interface XySysWorkNodeMapper extends Mapper<XySysWorkNode> {
    @Select("SELECT NODE_ID from XY_WORK_NODE\n" +
            "WHERE CONFIRMNODE_ID = #{nodeId}\n" +
            "OR CANCENODE_ID = #{nodeId}")
    public List<String> getPrevNodes(String nodeId);

    @Update("UPDATE XY_WORK_NODE SET NODE_TYPE = 3 \n" +
            "WHERE FLOW_ID = #{flowId,jdbcType=VARCHAR} \n" +
            "and CANCENODE_ID is null and CONFIRMNODE_ID is null ")
    public Integer updateOverNode(String flowId);
}
