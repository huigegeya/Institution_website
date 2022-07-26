package com.huige.Institution.service.impl;

import com.huige.Institution.dao.HonorMapper;
import com.huige.Institution.domain.entity.Honor;
import com.huige.Institution.service.IHonorService;
import com.huige.Institution.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HonorServiceImpl implements IHonorService {

    @Autowired
    private HonorMapper honorMapper;

    @Override
    public List<Honor> listPage(Honor honor) {
        PageUtil.start();
        List<Honor> honorList = honorMapper.select(honor);
        return honorList;
    }

    @Override
    public int add(Honor honor) {
        int rows = honorMapper.insert(honor);
        return rows;
    }
}
