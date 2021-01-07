package cn.edu.zjut.common.service;

import cn.edu.zjut.common.domain.Participation;

import java.util.List;

/**
 * Created by iris on 2020/12/27.
 */
public interface ParticipationService {
    int registerActivity(Long staffId, Long activityId);

    boolean enrollActivity(Participation participation);

    boolean quitActivity(Participation participation);

    List<Participation> listParticipation(Integer pageNum, Integer pageSize, Long activityId);

    List<Participation> listAllParticipation(Long activityId);

}
