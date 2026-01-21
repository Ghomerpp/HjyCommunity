package com.msb.hjycommunity.system.service;

import com.msb.hjycommunity.system.domain.SysDept;

import java.util.List;

public interface SysDeptService {
    public List<SysDept> selectDeptList(SysDept sysDept);
}
