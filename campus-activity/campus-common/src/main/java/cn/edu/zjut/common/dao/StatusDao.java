package cn.edu.zjut.common.dao;

import cn.edu.zjut.common.domain.Status;
public interface StatusDao {
    int createStatus(Status status);
    Long getStatusId(Long activityId);
    Long getLastId();
}
