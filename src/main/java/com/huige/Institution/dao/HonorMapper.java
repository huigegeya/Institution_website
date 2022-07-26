package com.huige.Institution.dao;

import com.huige.Institution.domain.entity.Honor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HonorMapper {

    public List<Honor> select(Honor honor);

    public int insert(Honor honor);
}
