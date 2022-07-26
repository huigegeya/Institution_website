package com.huige.Institution.service;

import com.huige.Institution.domain.entity.Honor;

import java.util.List;

public interface IHonorService {
    List<Honor> listPage(Honor honor);

    int add(Honor honor);
}
