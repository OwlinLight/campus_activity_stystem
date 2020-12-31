package cn.edu.zjut.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private long id;
    private int user_id;
    private int activity_id;
    private int rate;
    private String description;
}
