package cn.edu.zjut.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private long id;
    private int userId;
    private int activityId;
    private int rate;
    private String description;
}
