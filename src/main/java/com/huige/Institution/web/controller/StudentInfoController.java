package com.huige.Institution.web.controller;

import com.huige.Institution.domain.entity.StudentInfo;
import com.huige.Institution.domain.vo.AjaxResult;
import com.huige.Institution.service.IStudentInfoService;
import com.huige.Institution.validation.ValidGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.UUID;

@RestController
@RequestMapping("/blog/studentinfo")
@Validated
public class StudentInfoController {

    @Autowired
    private IStudentInfoService studentInfoService;

    private String imagePath = "";
    private String Path = "D:/file/";

    @PostMapping
    public AjaxResult add(@Validated(ValidGroup.Crud.Create.class) @RequestParam("file") MultipartFile file,
                                                                  @RequestParam("name") String name,
                                                                  @RequestParam("sex") String sex,
                                                                  @RequestParam("honor") String honor,
                                                                  @RequestParam("hobby") String hobby,
                                                                  @RequestParam("subject") String subject,
                                                                  @RequestParam("score") String score) {
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
        StudentInfo studentInfo = new StudentInfo();
        studentInfo.setSubject(subject);
        studentInfo.setHobby(hobby);
        studentInfo.setImage(imagePath);
        studentInfo.setName(name);
        studentInfo.setSex(sex);
        studentInfo.setHonor(honor);
        studentInfo.setScore(score);
        return AjaxResult.updateResult(studentInfoService.add(studentInfo), "添加");
    }

    @GetMapping("/listStudentInfo")
    public AjaxResult listStudentInfo() {
        return AjaxResult.pageResult(studentInfoService.listPage());
    }

    @PutMapping
    public AjaxResult updateStudentInfo(@Validated(ValidGroup.Crud.Update.class) @RequestBody StudentInfo studentInfo) {
        return AjaxResult.updateResult(studentInfoService.update(studentInfo), "修改");
    }

    @DeleteMapping("/{stuId}")
    public AjaxResult delete(@NotNull @PathVariable Integer stuId) {
        return AjaxResult.updateResult(studentInfoService.delete(stuId), "删除");
    }
}
