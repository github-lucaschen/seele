package com.skybeyondtech.oum.service;

import com.skybeyondtech.common.jpa.service.Service;
import com.skybeyondtech.oum.domain.dto.OumRoleDTO;
import com.skybeyondtech.oum.domain.po.OumRolePO;
import org.springframework.data.domain.Page;

public interface OumRoleService extends
        Service<OumRolePO, Long> {
    Page<OumRolePO> findAllByDTO(final OumRoleDTO oumRoleDTO);
}