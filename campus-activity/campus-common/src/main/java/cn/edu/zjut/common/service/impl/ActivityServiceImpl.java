package cn.edu.zjut.common.service.impl;

import cn.edu.zjut.common.dao.ActivityDao;
import cn.edu.zjut.common.domain.Activity;
import cn.edu.zjut.common.domain.Keywords;
import cn.edu.zjut.common.domain.Showac;
import cn.edu.zjut.common.domain.Status;
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
    public List<Showac> listAllActivity() {
        return activityDao.listActivityPassed();
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
    public int updateActivityStatus(Status status) {
        return activityDao.updateStatus(status);
    }

    @Override
    public int deleteActivity(Long id) {
        return activityDao.deleteActivity(id);
    }

    @Override
    public List<Showac> listActivityPassed(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return activityDao.listActivityPassed();
    }

    @Override
    public List<Showac> listActivityPending(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return activityDao.listActivityPending();
    }

    @Override
    public Activity getActivity(Long id) {
        return activityDao.getActivity(id);
    }

    @Override
    public List<Showac> askBykeywords(int pageNum, int pageSize, Keywords keywords) {
        PageHelper.startPage(pageNum, pageSize);
        return activityDao.askBykeywords(keywords);
    }

    @Override
    public List<Showac> getDirectorActivity(Long staffID,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return activityDao.getDirectorActivity(staffID);
    }
}
