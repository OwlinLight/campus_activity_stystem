package cn.edu.zjut.common.dao;

import cn.edu.zjut.common.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by iris on 2020/12/11.
 */
public interface ActivityInfoDao {

    ActivityInfo getActivityInfo(Long id);

    int updatePeopleLimit(@Param("id")Long id, @Param("limit")int limit);

}
