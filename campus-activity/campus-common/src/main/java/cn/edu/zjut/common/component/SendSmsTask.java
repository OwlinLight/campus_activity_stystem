package cn.edu.zjut.common.component;

import cn.edu.zjut.common.domain.Participation;
import cn.edu.zjut.common.domain.Showac;
import cn.edu.zjut.common.service.ActivityService;
import cn.edu.zjut.common.service.ParticipationService;
import cn.edu.zjut.common.service.SendSmsService;
import cn.edu.zjut.common.service.UserService;
import cn.hutool.core.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 定时任务：发送活动通知短信
 * Created by iris on 2020/12/31.
 */
@Component
public class SendSmsTask {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private SendSmsService sendSmsService;
    @Autowired
    private ParticipationService participationService;
    @Autowired
    private UserService userService;

    /**
     * cron表达式：Seconds Minutes Hours DayofMonth Month DayofWeek [Year]
     * "0 15 10 ? * *" 每天上午10:15触发
     */
    @Scheduled(cron = "0 35 18 ? * *")
    private void sendSmsOrder() {
        Date currentTime = DateUtil.date();
        List<Showac> showacs = activityService.listAllActivity();
        //对每一个符合条件的活动进行时间判断
        for (Showac showac : showacs) {
            Date startTime = showac.getStartTime();
            // 判读时间差距，两个时间相差多少天
            if (currentTime.before(startTime)) {
                Long diff = startTime.getTime() - currentTime.getTime();
                Long diffDays = diff / (1000 * 60 * 60 * 24);
                System.out.println("相差天数" + diffDays);
                //如果现在时间是活动开始时间前一天，就发送短信
                if (diffDays <= 1L) {
                    Long activityId = showac.getId();
                    List<Participation> participations = participationService.listAllParticipation(activityId);
                    //给每一个符合条件的用户发送短信
                    for (Participation participation : participations) {
                        Long staffId = participation.getStaffId();

                        String phone = "+86" + userService.getUser(staffId).getPhone();
                        System.out.println(phone);
                        String[] phoneNumberSet1 = {phone};

                        String format = DateUtil.format(startTime, "MM-dd HH:mm");
                        System.out.println(format);

                        String positionName = activityService.getPositionByActivityId(activityId);
                        System.out.println(positionName);

                        String[] templateParamSet1 = {participation.getActivityName(), format, positionName};
                        String templateID = "838807";

                        sendSmsService.sendSms(phoneNumberSet1, templateParamSet1, templateID);
                    }
                }
            }
        }
        //测试代码
//                String phone = "19858180151";
//        String[] phoneNumberSet1 = {"+86" + phone};
//        String[] templateParamSet1 = {"篮球比赛", "12-12 15:00", "篮球场"};
//        String templateID = "838807";
//        sendSmsService.sendSms(phoneNumberSet1, templateParamSet1, templateID);
    }
}
