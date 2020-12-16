package cn.edu.zjut.common.dao;

import cn.edu.zjut.common.domain.Activity;

import java.util.List;

/**
 * Created by iris on 2020/12/11.
 */
public interface ActivityDao {
    List<Activity> listAllActivity();

    int createActivity(Activity activity);

    int updateActivity(Activity activity);

    int deleteActivity(Long id);

    Activity getActivity(Long id);
}
