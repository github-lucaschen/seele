package com.skybeyondtech.oum.service.impl;

import com.skybeyondtech.common.jpa.repository.Repository;
import com.skybeyondtech.common.jpa.service.impl.ServiceImpl;
import com.skybeyondtech.oum.domain.dto.OumRoleDeptDTO;
import com.skybeyondtech.oum.domain.po.OumRoleDeptPO;
import com.skybeyondtech.oum.repository.OumRoleDeptRepository;
import com.skybeyondtech.oum.service.OumRoleDeptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OumRoleDeptServiceImpl extends ServiceImpl<OumRoleDeptPO, Long> implements OumRoleDeptService {

    private final OumRoleDeptRepository oumRoleDeptRepository;

    @Override
    public Repository<OumRoleDeptPO, Long> repository() {
        return oumRoleDeptRepository;
    }
}