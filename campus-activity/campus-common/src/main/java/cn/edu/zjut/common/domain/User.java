package cn.edu.zjut.common.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;



public class User implements Serializable{
    private Integer id;
    private String staff_id;
    private String password;
    private String name;
    private String role;
    private Integer collage_id;
    private String user_class;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getCollage_id() {
        return collage_id;
    }

    public void setCollage_id(Integer collage_id) {
        this.collage_id = collage_id;
    }

    public String getUser_class() {
        return user_class;
    }

    public void setUser_class(String user_class) {
        this.user_class = user_class;
    }
}
