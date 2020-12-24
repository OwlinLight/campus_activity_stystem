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
    private int people_limit;
    private int people_registered;
    private int people_enrolled;
    private int people_quitted;
    private String activity_img;
    private String activity_intro;
    private String result;
}
