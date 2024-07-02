package com.skybeyondtech.oum.service;

import com.skybeyondtech.common.jpa.service.Service;
import com.skybeyondtech.oum.domain.dto.OumMenuDTO;
import com.skybeyondtech.oum.domain.po.OumMenuPO;
import org.springframework.data.domain.Page;

public interface OumMenuService extends
        Service<OumMenuPO, Long> {
    Page<OumMenuPO> findAllByDTO(final OumMenuDTO oumMenuDTO);
}