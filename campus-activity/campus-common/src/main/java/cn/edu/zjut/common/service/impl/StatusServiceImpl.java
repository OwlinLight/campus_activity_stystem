package cn.edu.zjut.common.service.impl;

import cn.edu.zjut.common.dao.StatusDao;
import cn.edu.zjut.common.domain.Status;
import cn.edu.zjut.common.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl implements StatusService {
    @Autowired
    StatusDao statusDao;
    @Override
    public int createStatus(Status status) {
        return statusDao.createStatus(status);
    }

    @Override
    public Long getStatusId(Long activityId) {
        return statusDao.getStatusId(activityId);
    }

    @Override
    public Long getLastId() {
        return statusDao.getLastId();
    }
}
