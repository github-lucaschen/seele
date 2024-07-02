package com.skybeyondtech.oum.controller;

import com.skybeyondtech.base.enums.LogTypeEnum;
import com.skybeyondtech.base.enums.OperationTypeEnum;
import com.skybeyondtech.common.log.annotation.Log;
import com.skybeyondtech.oum.api.OumDepartmentApi;
import com.skybeyondtech.oum.convert.OumDepartmentDTOtoPOConverter;
import com.skybeyondtech.oum.convert.OumDepartmentPOtoVOConverter;
import com.skybeyondtech.oum.domain.dto.OumDepartmentDTO;
import com.skybeyondtech.oum.domain.po.OumDepartmentPO;
import com.skybeyondtech.oum.domain.vo.OumDepartmentVO;
import com.skybeyondtech.oum.service.OumDepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
@Tag(name = "部门管理")
@RequiredArgsConstructor
public class OumDepartmentController implements OumDepartmentApi {

    private final OumDepartmentService oumDepartmentService;

    @Override
    @Operation(summary = "分页查询数据")
    public Page<OumDepartmentVO> page(@ParameterObject final OumDepartmentDTO oumDepartmentDTO) {
        final Page<OumDepartmentPO> oumDepartmentPOPage =
                oumDepartmentService.findAllByDTO(oumDepartmentDTO);
        return OumDepartmentPOtoVOConverter.INSTANCE.toPage(oumDepartmentPOPage);
    }

    @Override
    @Operation(summary = "通过 id 查询")
    public OumDepartmentVO findById(final Long id) {
        return oumDepartmentService.findById(id)
                .map(OumDepartmentPOtoVOConverter.INSTANCE::toTarget)
                .orElse(null);
    }

    @Override
    @Operation(summary = "通过 id 删除")
    @Log(description = "department delete",
            logType = LogTypeEnum.OPERATION,
            operationType = OperationTypeEnum.DELETE)
    public void deleteById(final Long id) {
        oumDepartmentService.deleteById(id);
    }

    @Override
    @Operation(summary = "新增")
    @Log(description = "department save",
            logType = LogTypeEnum.OPERATION,
            operationType = OperationTypeEnum.INSERT)
    public Long save(final OumDepartmentDTO oumDepartmentDTO) {
        final OumDepartmentPO oumDepartmentPO =
                OumDepartmentDTOtoPOConverter.INSTANCE.toTarget(oumDepartmentDTO);
        return oumDepartmentService.save(oumDepartmentPO).getId();
    }
}
