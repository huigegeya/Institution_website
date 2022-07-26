package com.huige.Institution.domain.entity;

public class Apply {
    private Integer id;
    private Integer stuId;
    private Integer subjectId;
    private String teacher;
    private String subjectName;
    private String startTime;
    private String stuName;

    @Override
    public String toString() {
        return "Apply{" +
                "id=" + id +
                ", stuId=" + stuId +
                ", subjectId=" + subjectId +
                ", teacher='" + teacher + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", startTime='" + startTime + '\'' +
                ", stuName='" + stuName + '\'' +
                '}';
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
