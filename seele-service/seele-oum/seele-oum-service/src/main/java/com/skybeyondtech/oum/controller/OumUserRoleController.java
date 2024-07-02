package com.skybeyondtech.oum.controller;

import com.skybeyondtech.base.enums.LogTypeEnum;
import com.skybeyondtech.base.enums.OperationTypeEnum;
import com.skybeyondtech.common.log.annotation.Log;
import com.skybeyondtech.oum.api.OumUserRoleApi;
import com.skybeyondtech.oum.convert.OumUserRoleDTOtoPOConverter;
import com.skybeyondtech.oum.convert.OumUserRolePOtoVOConverter;
import com.skybeyondtech.oum.domain.dto.OumUserRoleDTO;
import com.skybeyondtech.oum.domain.po.OumUserRolePO;
import com.skybeyondtech.oum.domain.vo.OumUserRoleVO;
import com.skybeyondtech.oum.service.OumUserRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-role")
@Tag(name = "用户角色管理")
@RequiredArgsConstructor
public class OumUserRoleController implements OumUserRoleApi {

    private final OumUserRoleService oumUserRoleService;

    @Override
    @Operation(summary = "通过 id 查询")
    public OumUserRoleVO findById(final Long id) {
        return oumUserRoleService.findById(id)
                .map(OumUserRolePOtoVOConverter.INSTANCE::toTarget)
                .orElse(null);
    }

    @Override
    @Operation(summary = "通过 id 删除")
    @Log(description = "user-role delete",
            logType = LogTypeEnum.OPERATION,
            operationType = OperationTypeEnum.DELETE)
    public void deleteById(final Long id) {
        oumUserRoleService.deleteById(id);
    }

    @Override
    @Operation(summary = "新增")
    @Log(description = "user-role save",
            logType = LogTypeEnum.OPERATION,
            operationType = OperationTypeEnum.INSERT)
    public Long save(final OumUserRoleDTO oumUserRoleDTO) {
        final OumUserRolePO oumUserRolePO = OumUserRoleDTOtoPOConverter.INSTANCE.toTarget(oumUserRoleDTO);
        return oumUserRoleService.save(oumUserRolePO).getId();
    }

}
