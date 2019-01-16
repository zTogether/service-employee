package cn.xyzs.api.employee.utils;

import cn.xyzs.api.employee.entity.model.JournalInfo;
import cn.xyzs.api.employee.mapper.sys.log.JournalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * 日志工具类
 * @author zhou
 */
@Component
@Transactional
public class JournalUtils {

    @Resource
    private JournalMapper journalMapper;
    @Autowired
    private JournalInfo journalInfo;

    /**
     * 添加日志
     * @param modeularType
     * @param operationType
     */
    public void addJournalInfo(String modeularType,String operationType,String uid) {
        journalInfo.setModularType(modeularType);
        journalInfo.setOperationType(operationType);
        journalInfo.setUid(uid);
        journalMapper.insertSelective(journalInfo);
    }
}
