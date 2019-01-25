package cn.xyzs.api.employee.service.sys;

import cn.xyzs.api.employee.utils.XySqlUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Author: zhouchao
 * @Date: 2019/1/9 0009 14:35
 */
@Service
public class XyInitService {
    public List<LinkedHashMap<String,Object>> getAllUsers(){
        List<LinkedHashMap<String,Object>> list = new ArrayList<>();
        String sql = "select USER_ID,USER_CODE,USER_NAME,USER_TEL from XY_USER where IS_USED = 1";
        try {
            list = XySqlUtils.createSql(sql,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<LinkedHashMap<String,Object>> getAllRoles(){
        List<LinkedHashMap<String,Object>> list = new ArrayList<>();
        String sql = "select ROLE_ID,ROLE_NAME,ROLE_TYPE from XY_ROLE";
        try {
            list = XySqlUtils.createSql(sql,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
