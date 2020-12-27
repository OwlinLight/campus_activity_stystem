package cn.edu.zjut.common.service;

import cn.edu.zjut.common.domain.Participation;

/**
 * Created by iris on 2020/12/27.
 */
public interface ParticipationService {
    int registerActivity(Long staffId, Long activityId);

    boolean enrollActivity(Participation participation);
}
