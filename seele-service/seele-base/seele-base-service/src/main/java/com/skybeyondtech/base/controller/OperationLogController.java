package com.skybeyondtech.base.controller;

import com.skybeyondtech.base.api.OperationLogApi;
import com.skybeyondtech.base.convert.OperationLogDTOtoPOConverter;
import com.skybeyondtech.base.convert.OperationLogPOtoVOConverter;
import com.skybeyondtech.base.domain.dto.OperationLogDTO;
import com.skybeyondtech.base.domain.po.OperationLogPO;
import com.skybeyondtech.base.domain.vo.OperationLogVO;
import com.skybeyondtech.base.service.OperationLogService;
import com.skybeyondtech.common.log.annotation.Log;
import com.skybeyondtech.base.enums.LogTypeEnum;
import com.skybeyondtech.base.enums.OperationTypeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operation-log")
@Tag(name = "系统操作日志管理")
@RequiredArgsConstructor
public class OperationLogController implements OperationLogApi {

    private final OperationLogService operationLogService;

    @Override
    @Operation(summary = "分页查询数据")
    public Page<OperationLogVO> page(@ParameterObject final OperationLogDTO operationLogDTO) {
        final Page<OperationLogPO> operationLogPOPage = operationLogService.findAllByDTO(operationLogDTO);
        return OperationLogPOtoVOConverter.INSTANCE.toPage(operationLogPOPage);
    }

    @Override
    @Operation(summary = "通过 id 查询")
    @Log(description = "通过 id 查询",
            logType = LogTypeEnum.OPERATION,
            operationType = OperationTypeEnum.UNKNOWN)
    public OperationLogVO findById(final Long id) {
        return operationLogService.findById(id)
                .map(OperationLogPOtoVOConverter.INSTANCE::toTarget)
                .orElse(null);
    }

    @Override
    @Operation(summary = "通过 id 删除")
    @Log(description = "通过 id 删除",
            logType = LogTypeEnum.OPERATION,
            operationType = OperationTypeEnum.DELETE)
    public void deleteById(final Long id) {
        operationLogService.deleteById(id);
    }

    @Override
    @Operation(summary = "新增系统操作日志表")
    public Long save(final OperationLogDTO operationLogDTO) {
        final OperationLogPO operationLogPO =
                OperationLogDTOtoPOConverter.INSTANCE.toTarget(operationLogDTO);
        return operationLogService.save(operationLogPO).getId();
    }
}