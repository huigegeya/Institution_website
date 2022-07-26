package com.huige.Institution.domain.entity;

public class TeacherInfo {
    private Integer teacherId;
    private String name;
    private String sex;
    private String subject;
    private String hobby;
    private String honor;
    private String image;

    @Override
    public String toString() {
        return "TeacherInfo{" +
                "teacherId=" + teacherId +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", subject='" + subject + '\'' +
                ", hobby='" + hobby + '\'' +
                ", honor='" + honor + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
