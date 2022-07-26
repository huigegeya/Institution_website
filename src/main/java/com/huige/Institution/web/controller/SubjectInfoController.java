package com.huige.Institution.web.controller;

import com.huige.Institution.domain.entity.SubjectInfo;
import com.huige.Institution.domain.vo.AjaxResult;
import com.huige.Institution.service.ISubjectInfoService;
import com.huige.Institution.validation.ValidGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

@RestController
@RequestMapping("/blog/subjectinfo")
@Validated
public class SubjectInfoController {

    @Autowired
    private ISubjectInfoService subjectInfoService;

    @GetMapping("/listSubjectInfo")
    public AjaxResult listPage(SubjectInfo subjectInfo) {
        return AjaxResult.pageResult(subjectInfoService.listPage(subjectInfo));
    }


    @GetMapping("/{subjectId}")
    public AjaxResult get(@NotNull @PathVariable Integer subjectId) {
        SubjectInfo subjectInfo = subjectInfoService.getById(subjectId);
        return AjaxResult.success(subjectInfo);
    }

    @DeleteMapping("/{ids}")
    public AjaxResult delete(@NotEmpty(message = "课程id数组不能为空") @PathVariable Integer[] ids) {
        System.out.println(Arrays.toString(ids));
        return AjaxResult.updateResult(subjectInfoService.delete(ids), "删除课程");
    }

    @PostMapping
    public AjaxResult add(@Validated(ValidGroup.Crud.Create.class) @RequestBody SubjectInfo subjectInfo) {
        return AjaxResult.updateResult(subjectInfoService.add(subjectInfo), "添加课程");
    }

    @PutMapping
    public AjaxResult update(@Validated(ValidGroup.Crud.Update.class) @RequestBody SubjectInfo subjectInfo) {
        return AjaxResult.updateResult(subjectInfoService.update(subjectInfo), "修改课程");
    }
}
