package com.skybeyondtech.base.service;

import com.skybeyondtech.base.domain.dto.OperationLogDTO;
import com.skybeyondtech.base.domain.po.OperationLogPO;
import com.skybeyondtech.common.jpa.service.Service;
import org.springframework.data.domain.Page;

public interface OperationLogService extends
        Service<OperationLogPO, Long> {
    Page<OperationLogPO> findAllByDTO(final OperationLogDTO operationLogDTO);
}