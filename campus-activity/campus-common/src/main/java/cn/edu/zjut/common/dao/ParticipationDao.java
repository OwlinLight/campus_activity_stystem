package cn.edu.zjut.common.dao;

import cn.edu.zjut.common.domain.Participation;

import java.util.List;

/**
 * Created by iris on 2020/12/27.
 */
public interface ParticipationDao {
    int registerActivity(Participation participation);

    int updateParticipation(Participation participation);

    List<Participation> listParticipation(Long activityId);

    int findParticipation(Participation participation);
}
