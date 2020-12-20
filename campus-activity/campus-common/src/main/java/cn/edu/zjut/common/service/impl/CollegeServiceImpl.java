package cn.edu.zjut.common.service.impl;

import cn.edu.zjut.common.dao.CollegeDao;
import cn.edu.zjut.common.domain.College;
import cn.edu.zjut.common.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeServiceImpl implements CollegeService {
    @Autowired
    private CollegeDao collegeDao;

    @Override
    public List<College> listAllCollege() {
        return collegeDao.listAllCollege();
    }


}
