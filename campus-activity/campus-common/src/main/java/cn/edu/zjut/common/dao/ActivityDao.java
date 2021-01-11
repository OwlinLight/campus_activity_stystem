package cn.edu.zjut.common.dao;

import cn.edu.zjut.common.domain.Activity;
import cn.edu.zjut.common.domain.Keywords;
import cn.edu.zjut.common.domain.Status;
import cn.edu.zjut.common.domain.Showac;

import java.util.List;

/**
 * Created by iris on 2020/12/11.
 */
public interface ActivityDao {
    List<Showac> listActivityPassed();

    List<Showac> listActivityPending();

    int createActivity(Showac showac);

    int updateActivity(Activity activity);

    int updateStatus(Status status);

    int deleteActivity(Long id);

    Activity getActivity(Long id);

    List<Showac> askBykeywords(Keywords keywords);

    List<Showac> getDirectorActivity(Long staffID);

    Long getLastId();

    String getPositionByActivityId(Long activityId);

}
