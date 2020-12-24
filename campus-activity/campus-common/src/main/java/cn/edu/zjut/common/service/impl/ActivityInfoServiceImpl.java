package cn.edu.zjut.common.service.impl;

import cn.edu.zjut.common.dao.ActivityDao;
import cn.edu.zjut.common.dao.ActivityInfoDao;
import cn.edu.zjut.common.domain.Activity;
import cn.edu.zjut.common.domain.ActivityInfo;
import cn.edu.zjut.common.domain.Keywords;
import cn.edu.zjut.common.domain.Status;
import cn.edu.zjut.common.service.ActivityInfoService;
import cn.edu.zjut.common.service.ActivityService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by iris on 2020/12/11.
 */
@Service
public class ActivityInfoServiceImpl implements ActivityInfoService {

    @Autowired
    private ActivityInfoDao activityInfoDao;

    @Override
    public ActivityInfo getActivityInfo(long activity_id) {
        return activityInfoDao.getActivityInfo(activity_id);
    }

    @Override
    public int updatePeopleLimit(Long id, int limit) {
        return activityInfoDao.updatePeopleLimit(id, limit);
    }
}
