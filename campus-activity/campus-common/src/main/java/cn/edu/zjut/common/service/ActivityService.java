package cn.edu.zjut.common.service;

import cn.edu.zjut.common.domain.Activity;
import cn.edu.zjut.common.domain.Keywords;
import cn.edu.zjut.common.domain.Showac;
import cn.edu.zjut.common.domain.Status;

import java.util.List;

public interface ActivityService {

    List<Activity> listAllActivity();

    int createActivity(Activity activity);

    int updateActivity(Long id, Activity activity);

    int updateActivityStatus(Status status);

    int deleteActivity(Long id);

    List<Activity> listActivityPassed(int pageNum, int pageSize);

    List<Activity> listActivityFailed(int pageNum, int pageSize);

    Activity getActivity(Long id);

    List<Showac> askBykeywords(int pageNum, int pageSize, Keywords keywords);
}
