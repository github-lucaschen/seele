package com.skybeyondtech.oum.service;

import com.skybeyondtech.common.jpa.service.Service;
import com.skybeyondtech.oum.domain.dto.OumUserDTO;
import com.skybeyondtech.oum.domain.po.OumUserPO;
import org.springframework.data.domain.Page;

public interface OumUserService extends
        Service<OumUserPO, Long> {
    Page<OumUserPO> findAllByDTO(final OumUserDTO oumUserDTO);
}