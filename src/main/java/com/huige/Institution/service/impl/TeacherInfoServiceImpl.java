package com.huige.Institution.service.impl;

import com.huige.Institution.dao.TeacherInfoMapper;
import com.huige.Institution.domain.entity.TeacherInfo;
import com.huige.Institution.service.ITeacherInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeacherInfoServiceImpl implements ITeacherInfoService {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String TEACHERINFO_REDIS_KEY = "teacherInfo:";

    @Autowired
    private TeacherInfoMapper teacherInfoMapper;

    @Override
    public int add(TeacherInfo teacherInfo) {
        return teacherInfoMapper.insert(teacherInfo);
    }

    @Override
    public List<TeacherInfo> listPage() {

        return teacherInfoMapper.select();
    }

    @Transactional
    @Override
    public int delete(Integer teacherId) {
        int result = teacherInfoMapper.delete(teacherId);
        redisTemplate.delete("TEACHERINFO_REDIS_KEY" + teacherId);
        return result;
    }

    @Override
    public int update(TeacherInfo teacherInfo) {
        int rows = teacherInfoMapper.update(teacherInfo);
        // 清除缓存
        redisTemplate.delete(TEACHERINFO_REDIS_KEY + teacherInfo.getTeacherId());
        return rows;
    }
}
