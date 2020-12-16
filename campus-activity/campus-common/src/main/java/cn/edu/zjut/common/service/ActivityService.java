package cn.edu.zjut.common.service;

import cn.edu.zjut.common.domain.Activity;

import java.util.List;

public interface ActivityService {

    List<Activity> listAllActivity();

    int createActivity(Activity activity);

    int updateActivity(Long id, Activity activity);

    int deleteActivity(Long id);

    List<Activity> listActivity(int pageNum, int pageSize);

    Activity getActivity(Long id);
}
