package com.skybeyondtech.oum.controller;

import com.skybeyondtech.base.enums.LogTypeEnum;
import com.skybeyondtech.base.enums.OperationTypeEnum;
import com.skybeyondtech.common.log.annotation.Log;
import com.skybeyondtech.oum.api.OumRoleApi;
import com.skybeyondtech.oum.convert.OumRoleDTOtoPOConverter;
import com.skybeyondtech.oum.convert.OumRolePOtoVOConverter;
import com.skybeyondtech.oum.domain.dto.OumRoleDTO;
import com.skybeyondtech.oum.domain.po.OumRolePO;
import com.skybeyondtech.oum.domain.vo.OumRoleVO;
import com.skybeyondtech.oum.service.OumRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
@Tag(name = "角色管理")
@RequiredArgsConstructor
public class OumRoleController implements OumRoleApi {

    private final OumRoleService oumRoleService;

    @Override
    @Operation(summary = "分页查询数据")
    public Page<OumRoleVO> page(@ParameterObject final OumRoleDTO oumRoleDTO) {
        final Page<OumRolePO> oumRolePOPage = oumRoleService.findAllByDTO(oumRoleDTO);
        return OumRolePOtoVOConverter.INSTANCE.toPage(oumRolePOPage);
    }

    @Override
    @Operation(summary = "通过 id 查询")
    public OumRoleVO findById(final Long id) {
        return oumRoleService.findById(id)
                .map(OumRolePOtoVOConverter.INSTANCE::toTarget)
                .orElse(null);
    }

    @Override
    @Operation(summary = "通过 id 删除")
    @Log(description = "role delete",
            logType = LogTypeEnum.OPERATION,
            operationType = OperationTypeEnum.DELETE)
    public void deleteById(final Long id) {
        oumRoleService.deleteById(id);
    }

    @Override
    @Operation(summary = "新增")
    @Log(description = "role save",
            logType = LogTypeEnum.OPERATION,
            operationType = OperationTypeEnum.INSERT)
    public Long save(final OumRoleDTO oumRoleDTO) {
        final OumRolePO oumRolePO = OumRoleDTOtoPOConverter.INSTANCE.toTarget(oumRoleDTO);
        return oumRoleService.save(oumRolePO).getId();
    }
}
