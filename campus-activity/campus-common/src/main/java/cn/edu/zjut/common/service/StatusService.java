package cn.edu.zjut.common.service;

import cn.edu.zjut.common.domain.Status;



public interface StatusService {
    int createStatus(Status status);
    Long getStatusId(Long activityId);
    Long getLastId();
}
