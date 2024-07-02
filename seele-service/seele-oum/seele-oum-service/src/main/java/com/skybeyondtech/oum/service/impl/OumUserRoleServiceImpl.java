package com.skybeyondtech.oum.service.impl;

import com.skybeyondtech.common.jpa.repository.Repository;
import com.skybeyondtech.common.jpa.service.impl.ServiceImpl;
import com.skybeyondtech.oum.domain.dto.OumUserRoleDTO;
import com.skybeyondtech.oum.domain.po.OumUserRolePO;
import com.skybeyondtech.oum.repository.OumUserRoleRepository;
import com.skybeyondtech.oum.service.OumUserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OumUserRoleServiceImpl extends ServiceImpl<OumUserRolePO, Long> implements OumUserRoleService {

    private final OumUserRoleRepository oumUserRoleRepository;

    @Override
    public Repository<OumUserRolePO, Long> repository() {
        return oumUserRoleRepository;
    }
}