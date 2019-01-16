package cn.xyzs.api.employee.mapper.sys;

import cn.xyzs.api.employee.entity.po.sys.XySysRole;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: zhouchao
 * @Date: 2019/1/12 0012 10:43
 */
public interface XySysRoleMapper extends Mapper<XySysRole> {
    @Select("SELECT ROLE_ID from XY_SYS_ROLE\n" +
            "where ROLE_ID IN (\n" +
            "\tSELECT ROLE_ID from XY_SYS_USER_ROLE WHERE USER_ID = #{userId}\n" +
            ")")
    List<String> findRoleByUid(String userId);
}
