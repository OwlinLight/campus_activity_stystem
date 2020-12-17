package cn.edu.zjut.common.service.impl;

import cn.edu.zjut.common.dao.ActivityDao;
import cn.edu.zjut.common.domain.Activity;
import cn.edu.zjut.common.service.ActivityService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by iris on 2020/12/11.
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityDao activityDao;

    @Override
    public List<Activity> listAllActivity() {
        return activityDao.listAllActivity();
    }

    @Override
    public int createActivity(Activity activity) {
        return activityDao.createActivity(activity);
    }

    @Override
    public int updateActivity(Long id, Activity activity) {
        activity.setId(id);
        return activityDao.updateActivity(activity);
    }

    @Override
    public int deleteActivity(Long id) {
        return activityDao.deleteActivity(id);
    }

    @Override
    public List<Activity> listActivity(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return activityDao.listAllActivity();
    }

    @Override
    public Activity getActivity(Long id) {
        return activityDao.getActivity(id);
    }
}