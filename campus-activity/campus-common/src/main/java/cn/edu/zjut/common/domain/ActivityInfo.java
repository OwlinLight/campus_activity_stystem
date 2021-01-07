package cn.edu.zjut.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ActivityInfo implements Serializable {
    private Long id;
    private String name;
    private Long activityId;
    private int people_limit;
    private int people_registered;
    private int people_enrolled;
    private int people_quitted;
    private String activity_img;
    private String activity_intro;
    private String result;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public int getPeople_limit() {
        return people_limit;
    }

    public void setPeople_limit(int people_limit) {
        this.people_limit = people_limit;
    }

    public int getPeople_registered() {
        return people_registered;
    }

    public void setPeople_registered(int people_registered) {
        this.people_registered = people_registered;
    }

    public int getPeople_enrolled() {
        return people_enrolled;
    }

    public void setPeople_enrolled(int people_enrolled) {
        this.people_enrolled = people_enrolled;
    }

    public int getPeople_quitted() {
        return people_quitted;
    }

    public void setPeople_quitted(int people_quitted) {
        this.people_quitted = people_quitted;
    }

    public String getActivity_img() {
        return activity_img;
    }

    public void setActivity_img(String activity_img) {
        this.activity_img = activity_img;
    }

    public String getActivity_intro() {
        return activity_intro;
    }

    public void setActivity_intro(String activity_intro) {
        this.activity_intro = activity_intro;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
