package com.skybeyondtech.oum.service.impl;

import com.skybeyondtech.common.jpa.repository.Repository;
import com.skybeyondtech.common.jpa.service.impl.ServiceImpl;
import com.skybeyondtech.oum.domain.dto.OumRoleMenuDTO;
import com.skybeyondtech.oum.domain.po.OumRoleMenuPO;
import com.skybeyondtech.oum.repository.OumRoleMenuRepository;
import com.skybeyondtech.oum.service.OumRoleMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OumRoleMenuServiceImpl extends ServiceImpl<OumRoleMenuPO, Long> implements OumRoleMenuService {

    private final OumRoleMenuRepository oumRoleMenuRepository;

    @Override
    public Repository<OumRoleMenuPO, Long> repository() {
        return oumRoleMenuRepository;
    }
}