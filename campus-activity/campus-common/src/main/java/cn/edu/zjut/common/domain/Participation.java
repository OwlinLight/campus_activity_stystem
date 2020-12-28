package cn.edu.zjut.common.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by iris on 2020/12/27.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Participation {
    private Long id;
    private Long staffId;
    private Long activityId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date registerTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date enrollTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date quitTime;
    private String staffName;
    private String activityName;
    private String collegeName;
    private String className;
}
