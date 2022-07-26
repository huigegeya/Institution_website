package com.huige.Institution.domain.entity;

public class Honor {
    private Integer honorWinnerId;
    private String honorWinner;
    private String time;
    private String Subject;
    private String honorContent;

    @Override
    public String toString() {
        return "Honor{" +
                "honorWinnerId=" + honorWinnerId +
                ", honorWinner='" + honorWinner + '\'' +
                ", time='" + time + '\'' +
                ", Subject='" + Subject + '\'' +
                ", honorContent='" + honorContent + '\'' +
                '}';
    }

    public Integer getHonorWinnerId() {
        return honorWinnerId;
    }

    public void setHonorWinnerId(Integer honorWinnerId) {
        this.honorWinnerId = honorWinnerId;
    }

    public String getHonorWinner() {
        return honorWinner;
    }

    public void setHonorWinner(String honorWinner) {
        this.honorWinner = honorWinner;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getHonorContent() {
        return honorContent;
    }

    public void setHonorContent(String honorContent) {
        this.honorContent = honorContent;
    }
}
