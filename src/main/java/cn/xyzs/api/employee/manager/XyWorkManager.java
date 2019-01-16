package cn.xyzs.api.employee.manager;

import cn.xyzs.api.employee.entity.po.sys.XySysWorkActive;
import cn.xyzs.api.employee.entity.po.sys.XySysWorkNode;
import cn.xyzs.api.employee.mapper.sys.flow.XySysWorkActiveMapper;
import cn.xyzs.api.employee.mapper.sys.flow.XySysWorkNodeMapper;
import cn.xyzs.api.employee.utils.XySqlUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhouchao
 * @Date: 2018/12/29 0029 14:34
 * 工作流节点配置，
 */
@Service
public class XyWorkManager {

    @Resource
    private XySysWorkActiveMapper activeMapper;

    @Resource
    private XySysWorkNodeMapper nodeMapper;


    /**
     * 新增工作流
     * @param XySysWorkActive
     * @return
     */
    public Integer addWorkFlow(XySysWorkActive XySysWorkActive){
        XySysWorkActive.setActAddtime(System.currentTimeMillis());
        return activeMapper.insertSelective(XySysWorkActive);
    }
    /**
     * 新增节点
     * @return
     */
    public String addNode(XySysWorkNode XySysWorkNode, String mnodeId, String cenodeId){
        //判断是不是开始节点
        if("1".equals(XySysWorkNode.getNodeType())){
            int r = nodeMapper.insertSelective(XySysWorkNode);
            return XySysWorkNode.getNodeId();
        }
        //查询上级节点，防止上级节点在这期间被删除
        XySysWorkNode workNode_t=null,workNode_f=null;
        if(mnodeId!=null||!"".equals(mnodeId)){
            workNode_t = nodeMapper.selectByPrimaryKey(mnodeId);
        }
        if(cenodeId!=null||!"".equals(cenodeId)){
            workNode_f = nodeMapper.selectByPrimaryKey(cenodeId);
        }
        if (workNode_t==null&&workNode_f==null){
            return "0";
        }else{
            //新增节点
            nodeMapper.insertSelective(XySysWorkNode);
            //修改上级节点确认|取消
            if(workNode_t!=null){
                workNode_t.setConfirmnodeId(XySysWorkNode.getNodeId());
                nodeMapper.updateByPrimaryKeySelective(workNode_t);
            }
            if (workNode_f!=null){
                workNode_f.setCancenodeId(XySysWorkNode.getNodeId());
                nodeMapper.updateByPrimaryKeySelective(workNode_f);
            }
        }
        return XySysWorkNode.getNodeId();
    }
    /**
     * 删除节点
     * @return
     */
    public Integer delNode(String nodeId){
        XySysWorkNode XySysWorkNode = nodeMapper.selectByPrimaryKey(nodeId);
        if(XySysWorkNode==null){
            return 1;
        }
        //判断有没有下级节点，如果有下级节点则不可以删除
        if(XySysWorkNode.getConfirmnodeId()!=null||XySysWorkNode.getConfirmnodeId()!=null){
            return 0;
        }
        //执行删除
        nodeMapper.deleteByPrimaryKey(nodeId);
        //清空上级绑定数据
        Example example = new Example(XySysWorkNode.class);
        example.and().andEqualTo("confirmnodeId",nodeId).orEqualTo("cancenodeId",nodeId);
        List<XySysWorkNode> nodes = nodeMapper.selectByExample(example);
        for (XySysWorkNode node:nodes) {
            if(nodeId.equals(node.getConfirmnodeId())){
                node.setConfirmnodeId("");
            }
            if(nodeId.equals(node.getCancenodeId())){
                node.setCancenodeId("");
            }
            nodeMapper.updateByPrimaryKeySelective(node);
        }
        return 1;
    }

    /**
     * 查询所有工作流
     * @return
     */
    public List<XySysWorkActive> getWorkFlows(){
        return activeMapper.selectAll();
    }

    /**
     * 查询工作流
     * @param flowId
     * @return
     */
    public XySysWorkActive getWorkFlow(String flowId){
        return activeMapper.selectByPrimaryKey(flowId);
    }

    /**
     *  获取当前工作流,第一个节点
     * @param flow
     * @return
     */
    public XySysWorkNode getFirstNode(String flow){
        XySysWorkNode node = new XySysWorkNode();
        node.setFlowId(flow);
        node.setNodeType("1");
        XySysWorkNode XySysWorkNode = nodeMapper.selectOne(node);
        return  XySysWorkNode;
    }
    /**
     * 获取当前节点的下节点
     * @param nodeId
     * @return
     */
    public List<XySysWorkNode> getNextNodes(String nodeId){
        XySysWorkNode XySysWorkNode = nodeMapper.selectByPrimaryKey(nodeId);
        Example example = new Example(XySysWorkNode.class);
        example.and().andEqualTo("nodeId",XySysWorkNode.getConfirmnodeId()).orEqualTo("nodeId",XySysWorkNode.getCancenodeId());
        List<XySysWorkNode> XySysWorkNodes = nodeMapper.selectByExample(example);
        return XySysWorkNodes;
    }
    /**
     * 获取当前节点的下节点，
     * @param nodeId
     * @param b 是，否
     * @return
     */
    public XySysWorkNode getNextNode(String nodeId,Boolean b){
        XySysWorkNode XySysWorkNode = nodeMapper.selectByPrimaryKey(nodeId);
        XySysWorkNode workNode = null;
        if(b){
            return workNode = nodeMapper.selectByPrimaryKey(XySysWorkNode.getConfirmnodeId());
        }else{
            return workNode = nodeMapper.selectByPrimaryKey(XySysWorkNode.getCancenodeId());
        }
    }

    /**
     * 确认最终节点，在提交的时候进行
     * @param flowId
     * @return
     */
    public Integer overNode(String flowId){
        nodeMapper.updateOverNode(flowId);
        XySysWorkActive active = activeMapper.selectByPrimaryKey(flowId);
        active.setActIsdel(1);
        activeMapper.updateByPrimaryKeySelective(active);
        return 1;
    }

    public List<LinkedHashMap<String,Object>> getNodeByFlow(String flowId){
        Map<String,Object> map = new HashMap<>(16);
        map.put("flowId",flowId);
        String sql = "SELECT NODE_ID nodeId," +
                "FLOW_ID flowId," +
                "NODE_NAME nodeName," +
                "CANCENODE_ID cancenodeId," +
                "CONFIRMNODE_ID confirmnodeId," +
                "NODE_TYPE nodeType," +
                "NODE_ISDEL nodeIsdel," +
                "NODE_ADDTIME nodeAddtime," +
                "CONFIRMNODE_BUTTON confirmnodeButton," +
                "CANCENODE_BUTTON cancenodeButton," +
                "NODE_OPERATION_USER nodeOperationUser," +
                "XU.USER_NAME," +
                "NODE_OPERATION_GROUP nodeOperationGroup," +
                "XWG.GROUP_NAME," +
                "NODE_POSITION nodePosition," +
                "IS_COPY isCopy " +
                "FROM XY_WORK_NODE XWN " +
                "LEFT JOIN XY_WORK_GROUP XWG ON XWN.NODE_OPERATION_GROUP=XWG.GROUP_ID AND XWN.NODE_OPERATION_GROUP IS NOT NULL " +
                "LEFT JOIN XY_USER XU ON XU.USER_ID=XWN.NODE_OPERATION_USER AND XWN.NODE_OPERATION_GROUP IS NOT NULL " +
                "WHERE ( FLOW_ID = #{flowId} ) " +
                "order by NODE_ADDTIME ASC";
        List<LinkedHashMap<String,Object>> list = null;
        try {
            list = XySqlUtil.createSql(sql,map);
            System.err.println(JSON.toJSONString(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
        /**
         XySysWorkNode workNode = new XySysWorkNode();
         workNode.setFlowId(flowId);
         Example example = new Example(XySysWorkNode.class);
         example.setOrderByClause("NODE_ADDTIME ASC");
         example.createCriteria().andEqualTo("flowId",flowId);
         List<XySysWorkNode> workNodes = nodeMapper.selectByExample(example);
         System.err.println(JSON.toJSONString(workNodes));
         return workNodes;
         **/
    }

    public List<String> getPrevNodes(String nodeId){
        return nodeMapper.getPrevNodes(nodeId);
    }
}
