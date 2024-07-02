package com.skybeyondtech.oum.service;

import com.skybeyondtech.common.jpa.service.Service;
import com.skybeyondtech.oum.domain.dto.OumPostDTO;
import com.skybeyondtech.oum.domain.po.OumPostPO;
import org.springframework.data.domain.Page;

public interface OumPostService extends
        Service<OumPostPO, Long> {
    Page<OumPostPO> findAllByDTO(final OumPostDTO oumPostDTO);
}