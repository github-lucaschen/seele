package com.skybeyondtech.oum.service;

import com.skybeyondtech.common.jpa.service.Service;
import com.skybeyondtech.oum.domain.dto.OumDepartmentDTO;
import com.skybeyondtech.oum.domain.po.OumDepartmentPO;
import org.springframework.data.domain.Page;

public interface OumDepartmentService extends
        Service<OumDepartmentPO, Long> {
    Page<OumDepartmentPO> findAllByDTO(final OumDepartmentDTO oumDepartmentDTO);
}