package com.skybeyondtech.oum.controller;

import com.skybeyondtech.base.enums.LogTypeEnum;
import com.skybeyondtech.base.enums.OperationTypeEnum;
import com.skybeyondtech.common.log.annotation.Log;
import com.skybeyondtech.oum.api.OumRoleMenuApi;
import com.skybeyondtech.oum.convert.OumRoleMenuDTOtoPOConverter;
import com.skybeyondtech.oum.convert.OumRoleMenuPOtoVOConverter;
import com.skybeyondtech.oum.domain.dto.OumRoleMenuDTO;
import com.skybeyondtech.oum.domain.po.OumRoleMenuPO;
import com.skybeyondtech.oum.domain.vo.OumRoleMenuVO;
import com.skybeyondtech.oum.service.OumRoleMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role-menu")
@Tag(name = "角色菜单管理")
@RequiredArgsConstructor
public class OumRoleMenuController implements OumRoleMenuApi {

    private final OumRoleMenuService oumRoleMenuService;

    @Override
    @Operation(summary = "通过 id 查询")
    public OumRoleMenuVO findById(final Long id) {
        return oumRoleMenuService.findById(id)
                .map(OumRoleMenuPOtoVOConverter.INSTANCE::toTarget)
                .orElse(null);
    }

    @Override
    @Operation(summary = "通过 id 删除")
    @Log(description = "role-menu delete",
            logType = LogTypeEnum.OPERATION,
            operationType = OperationTypeEnum.DELETE)
    public void deleteById(final Long id) {
        oumRoleMenuService.deleteById(id);
    }

    @Override
    @Operation(summary = "新增")
    @Log(description = "role-menu save",
            logType = LogTypeEnum.OPERATION,
            operationType = OperationTypeEnum.INSERT)
    public Long save(final OumRoleMenuDTO oumRoleMenuDTO) {
        final OumRoleMenuPO oumRoleMenuPO = OumRoleMenuDTOtoPOConverter.INSTANCE.toTarget(oumRoleMenuDTO);
        return oumRoleMenuService.save(oumRoleMenuPO).getId();
    }
}
