package com.huige.Institution.web.controller;

import com.huige.Institution.domain.entity.TeacherInfo;
import com.huige.Institution.domain.vo.AjaxResult;
import com.huige.Institution.service.ITeacherInfoService;
import com.huige.Institution.validation.ValidGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.UUID;

@RestController
@RequestMapping("/blog/teacherinfo")
@Validated
public class TeacherInfoController {

    @Autowired
    private ITeacherInfoService teacherInfoService;

    private String imagePath = "";
    private String Path = "D:/file/";

    @PostMapping
    public AjaxResult add(@Validated(ValidGroup.Crud.Create.class) @RequestParam("file") MultipartFile file,
                                                                  @RequestParam("name") String name,
                                                                  @RequestParam("sex") String sex,
                                                                  @RequestParam("honor") String honor,
                                                                  @RequestParam("hobby") String hobby,
                                                                  @RequestParam("subject") String subject) {
        try {
            String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String filename = UUID.randomUUID() + suffixName;
            File filePath = new File(Path);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            //创建虚拟路径存储
            imagePath = "http://localhost:8093/image/" + filename;
            file.transferTo(new File(Path + filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
        TeacherInfo teacherInfo = new TeacherInfo();
        teacherInfo.setSubject(subject);
        teacherInfo.setHobby(hobby);
        teacherInfo.setImage(imagePath);
        teacherInfo.setName(name);
        teacherInfo.setSex(sex);
        teacherInfo.setHonor(honor);
        return AjaxResult.updateResult(teacherInfoService.add(teacherInfo), "添加教师信息");
    }

    @GetMapping("/listTeacherInfo")
    public AjaxResult listTeacherInfo() {
        return AjaxResult.pageResult(teacherInfoService.listPage());
    }

    @PutMapping
    public AjaxResult updateTeacherInfo(@Validated(ValidGroup.Crud.Update.class) @RequestBody TeacherInfo teacherInfo) {
        return AjaxResult.updateResult(teacherInfoService.update(teacherInfo), "修改");
    }

    @DeleteMapping("/{teacherId}")
    public AjaxResult delete(@NotNull @PathVariable Integer teacherId) {
        return AjaxResult.updateResult(teacherInfoService.delete(teacherId), "删除");
    }
}
