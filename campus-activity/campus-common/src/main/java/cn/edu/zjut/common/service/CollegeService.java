package cn.edu.zjut.common.service;

import cn.edu.zjut.common.domain.College;

import java.util.List;

public interface CollegeService {
    List<College> listAllCollege();
    Long getCollegeId(String collegeName);
    College getCollege(Long id);
}
