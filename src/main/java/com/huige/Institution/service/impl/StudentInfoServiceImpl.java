package com.huige.Institution.service.impl;

import com.huige.Institution.dao.StudentInfoMapper;
import com.huige.Institution.domain.entity.StudentInfo;
import com.huige.Institution.service.IStudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentInfoServiceImpl implements IStudentInfoService {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String STUDENTINFO_REDIS_KEY = "studentInfo:";

    @Autowired
    private StudentInfoMapper studentInfoMapper;

    @Override
    public int add(StudentInfo studentInfo) {
        return studentInfoMapper.insert(studentInfo);
    }

    @Override
    public List<StudentInfo> listPage() {

        return studentInfoMapper.select();
    }

    @Transactional
    @Override
    public int delete(Integer stuId) {
        int result = studentInfoMapper.delete(stuId);
        redisTemplate.delete("STUDENTINFO_REDIS_KEY" + stuId);
        return result;
    }

    @Override
    public int update(StudentInfo studentInfo) {
        int rows = studentInfoMapper.update(studentInfo);
        // 清除缓存
        redisTemplate.delete(STUDENTINFO_REDIS_KEY + studentInfo.getStuId());
        return rows;
    }
}
