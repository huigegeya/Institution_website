package com.huige.Institution.service.impl;

import com.huige.Institution.dao.SubjectInfoMapper;
import com.huige.Institution.dao.UserMapper;
import com.huige.Institution.domain.entity.SubjectInfo;
import com.huige.Institution.service.ISubjectInfoService;
import com.huige.Institution.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubjectInfoServiceImpl implements ISubjectInfoService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SubjectInfoMapper subjectInfoMapper;
    @Autowired
    private UserMapper userMapper;

    // 文章的 redis key
    private static final String SUBJECTINFO_REDIS_KEY = "subjectInfo:";
    @Override
    public List<SubjectInfo> listPage(SubjectInfo subjectInfo) {
        PageUtil.start();
        List<SubjectInfo> subjectInfoList = subjectInfoMapper.select(subjectInfo);
        return subjectInfoList;
    }


    @Override
    public SubjectInfo getById(Integer subjectId) {
        SubjectInfo subjectInfo = subjectInfoMapper.selectById(subjectId);
        return subjectInfo;
    }

    @Transactional
    @Override
    public int delete(Integer[] ids) {
        int result = 0;
        for (Integer id : ids) {
            userMapper.deleteBySubjectId(id);
            result += subjectInfoMapper.delete(id);
            redisTemplate.delete("SUBJECTINFO_REDIS_KEY" + id);
        }
        return result;
    }

    @Override
    public int update(SubjectInfo subjectInfo) {
        int rows = subjectInfoMapper.update(subjectInfo);
        // 清除缓存
        redisTemplate.delete(SUBJECTINFO_REDIS_KEY + subjectInfo.getSubjectId());
        return rows;
    }

    @Transactional
    @Override
    public int add(SubjectInfo subjectInfo) {
        subjectInfo.setPeopleNum(0);
        // 插入文章
        int rows = subjectInfoMapper.insert(subjectInfo);
        return rows;
    }
}
