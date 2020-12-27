package cn.edu.zjut.common.dao;

import cn.edu.zjut.common.domain.Participation;

/**
 * Created by iris on 2020/12/27.
 */
public interface ParticipationDao {
    int registerActivity(Participation participation);

    int updateParticipation(Participation participation);
}
