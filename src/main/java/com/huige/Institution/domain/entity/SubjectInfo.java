package com.huige.Institution.domain.entity;

public class SubjectInfo {
    private Integer subjectId;
    private String subjectName;
    private String teacher;
    private String startTime;
    private Integer classTime;
    private Integer peopleNum;
    private Integer maxNum;

    @Override
    public String toString() {
        return "SubjectInfo{" +
                "subjectId=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                ", teacher='" + teacher + '\'' +
                ", startTime=" + startTime +
                ", classTime=" + classTime +
                ", peopleNum=" + peopleNum +
                ", maxNum=" + maxNum +
                '}';
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Integer getClassTime() {
        return classTime;
    }

    public void setClassTime(Integer classTime) {
        this.classTime = classTime;
    }

    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
    }

    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }
}
