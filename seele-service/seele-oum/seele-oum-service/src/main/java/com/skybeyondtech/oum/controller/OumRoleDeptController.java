package com.skybeyondtech.oum.controller;

import com.skybeyondtech.base.enums.LogTypeEnum;
import com.skybeyondtech.base.enums.OperationTypeEnum;
import com.skybeyondtech.common.log.annotation.Log;
import com.skybeyondtech.oum.api.OumRoleDeptApi;
import com.skybeyondtech.oum.convert.OumRoleDeptDTOtoPOConverter;
import com.skybeyondtech.oum.convert.OumRoleDeptPOtoVOConverter;
import com.skybeyondtech.oum.domain.dto.OumRoleDeptDTO;
import com.skybeyondtech.oum.domain.po.OumRoleDeptPO;
import com.skybeyondtech.oum.domain.vo.OumRoleDeptVO;
import com.skybeyondtech.oum.service.OumRoleDeptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role-dept")
@Tag(name = "角色部门管理")
@RequiredArgsConstructor
public class OumRoleDeptController implements OumRoleDeptApi {

    private final OumRoleDeptService oumRoleDeptService;

    @Override
    @Operation(summary = "通过 id 查询")
    public OumRoleDeptVO findById(final Long id) {
        return oumRoleDeptService.findById(id)
                .map(OumRoleDeptPOtoVOConverter.INSTANCE::toTarget)
                .orElse(null);
    }

    @Override
    @Operation(summary = "通过 id 删除")
    @Log(description = "role-dept delete",
            logType = LogTypeEnum.OPERATION,
            operationType = OperationTypeEnum.DELETE)
    public void deleteById(final Long id) {
        oumRoleDeptService.deleteById(id);
    }

    @Override
    @Operation(summary = "新增")
    @Log(description = "role-dept save",
            logType = LogTypeEnum.OPERATION,
            operationType = OperationTypeEnum.INSERT)
    public Long save(final OumRoleDeptDTO oumRoleDeptDTO) {
        final OumRoleDeptPO oumRoleDeptPO = OumRoleDeptDTOtoPOConverter.INSTANCE.toTarget(oumRoleDeptDTO);
        return oumRoleDeptService.save(oumRoleDeptPO).getId();
    }
}
