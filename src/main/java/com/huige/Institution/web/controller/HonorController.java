package com.huige.Institution.web.controller;

import com.huige.Institution.domain.entity.Honor;
import com.huige.Institution.domain.vo.AjaxResult;
import com.huige.Institution.service.IHonorService;
import com.huige.Institution.validation.ValidGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog/honor")
@Validated
public class HonorController {

    @Autowired
    private IHonorService honorService;

    @GetMapping("/listPage")
    public AjaxResult listPage(Honor honor) {
        return AjaxResult.pageResult(honorService.listPage(honor));
    }

    @PostMapping("/addHonor")
    public AjaxResult add(@Validated(ValidGroup.Crud.Create.class) @RequestBody Honor honor) {
        return AjaxResult.updateResult(honorService.add(honor), "添加");
    }
}
