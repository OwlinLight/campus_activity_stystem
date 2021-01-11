package cn.edu.zjut.common.service.impl;

import cn.edu.zjut.common.dao.ActivityDao;
import cn.edu.zjut.common.dao.ActivityInfoDao;
import cn.edu.zjut.common.dao.ParticipationDao;
import cn.edu.zjut.common.domain.Activity;
import cn.edu.zjut.common.domain.ActivityInfo;
import cn.edu.zjut.common.domain.Participation;
import cn.edu.zjut.common.service.ParticipationService;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import io.swagger.models.auth.In;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by iris on 2020/12/27.
 */
@Service
public class ParticipationServiceImpl implements ParticipationService {
    @Autowired
    private ParticipationDao participationDao;
    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private ActivityInfoDao activityInfoDao;

    @Override
    public int registerActivity(Long staffId, Long activityId) {
        //当前时间
        Date registerTime = DateUtil.date();
        Participation participation = new Participation();
        participation.setStaffId(staffId);
        participation.setActivityId(activityId);
        participation.setRegisterTime(registerTime);
        int flag = participationDao.findParticipation(participation);
        if (flag >= 1) {
            return 0;
        }
        //更新报名人数
        ActivityInfo activityInfo = activityInfoDao.getActivityInfo(activityId);
        activityInfo.setPeople_registered(activityInfo.getPeople_registered() + 1);
        activityInfoDao.updateActivityInfoPeople(activityInfo);
        return participationDao.registerActivity(participation);
    }

    @Override
    public boolean enrollActivity(Participation participation) {
        //可以提前30min签到，活动开始30min后禁止签到
        int flag = participationDao.findParticipation(participation);
        if (flag != 1) {
            return false;
        }
        Date enrollTime = DateUtil.date();
        Activity activity = activityDao.getActivity(participation.getActivityId());
        Date startTime = activity.getStartTime();
        Date endTime = activity.getEndTime();
        if (enrollTime.after(startTime) && enrollTime.before(endTime)) {
            Long start_enroll_diff = enrollTime.getTime() - startTime.getTime();
            System.out.println("相差分钟数" + start_enroll_diff / 60 / 1000);
            //用毫秒值除以分钟再除以毫秒即得两个时间相差的分钟数
            if (start_enroll_diff / 60 / 1000 <= 30) {
                participation.setEnrollTime(enrollTime);
                int count = participationDao.updateParticipation(participation);
                if (count == 1) {
                    //更新签到人数
                    ActivityInfo activityInfo = activityInfoDao.getActivityInfo(participation.getActivityId());
                    activityInfo.setPeople_enrolled(activityInfo.getPeople_enrolled() + 1);
                    activityInfoDao.updateActivityInfoPeople(activityInfo);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean quitActivity(Participation participation) {
        //活动结束后15min内签退
        int flag = participationDao.findParticipation(participation);
        if (flag != 1) {
            return false;
        }
        Date quitTime = DateUtil.date();
        Activity activity = activityDao.getActivity(participation.getActivityId());
        Date endTime = activity.getEndTime();
        if (quitTime.after(endTime)) {
            Long end_quit_diff = quitTime.getTime() - endTime.getTime();
            System.out.println("相差分钟数" + end_quit_diff / 60 / 1000);
            //用毫秒值除以分钟再除以毫秒即得两个时间相差的分钟数
            if (end_quit_diff / 60 / 1000 <= 15) {
                participation.setQuitTime(quitTime);
                int count = participationDao.updateParticipation(participation);
                if (count == 1) {
                    //更新签退人数
                    ActivityInfo activityInfo = activityInfoDao.getActivityInfo(participation.getActivityId());
                    activityInfo.setPeople_enrolled(activityInfo.getPeople_quitted() + 1);
                    activityInfoDao.updateActivityInfoPeople(activityInfo);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<Participation> listParticipation(Integer pageNum, Integer pageSize, Long activityId) {
        PageHelper.startPage(pageNum, pageSize);
        return participationDao.listParticipation(activityId);
    }

    @Override
    public List<Participation> listAllParticipation(Long activityId) {
        return participationDao.listParticipation(activityId);
    }

    @Override
    public int updateStaff_id(Long staffId){ return participationDao.updateStaff_id(staffId);}

}
