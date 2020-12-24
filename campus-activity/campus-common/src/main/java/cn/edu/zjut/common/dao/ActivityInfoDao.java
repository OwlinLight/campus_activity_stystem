package cn.edu.zjut.common.dao;

import cn.edu.zjut.common.domain.Activity;
import cn.edu.zjut.common.domain.ActivityInfo;
import cn.edu.zjut.common.domain.Keywords;
import cn.edu.zjut.common.domain.Status;

import java.util.List;

/**
 * Created by iris on 2020/12/11.
 */
public interface ActivityInfoDao {

    ActivityInfo getActivityInfo(Long id);
}
