package com.huige.Institution.domain.entity;

public class StudentInfo {
    private Integer stuId;
    private String name;
    private String sex;
    private String subject;
    private String hobby;
    private String honor;
    private String score;
    private String image;

    @Override
    public String toString() {
        return "StudentInfo{" +
                "stuId=" + stuId +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", subject='" + subject + '\'' +
                ", hobby='" + hobby + '\'' +
                ", honor='" + honor + '\'' +
                ", score='" + score + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
