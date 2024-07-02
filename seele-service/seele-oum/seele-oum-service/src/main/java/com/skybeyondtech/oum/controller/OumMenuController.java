package com.skybeyondtech.oum.controller;

import com.skybeyondtech.base.enums.LogTypeEnum;
import com.skybeyondtech.base.enums.OperationTypeEnum;
import com.skybeyondtech.common.log.annotation.Log;
import com.skybeyondtech.oum.api.OumMenuApi;
import com.skybeyondtech.oum.convert.OumMenuDTOtoPOConverter;
import com.skybeyondtech.oum.convert.OumMenuPOtoVOConverter;
import com.skybeyondtech.oum.domain.dto.OumMenuDTO;
import com.skybeyondtech.oum.domain.po.OumMenuPO;
import com.skybeyondtech.oum.domain.vo.OumMenuVO;
import com.skybeyondtech.oum.service.OumMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
@Tag(name = "菜单管理")
@RequiredArgsConstructor
public class OumMenuController implements OumMenuApi {

    private final OumMenuService oumMenuService;

    @Override
    @Operation(summary = "分页查询数据")
    public Page<OumMenuVO> page(@ParameterObject final OumMenuDTO oumMenuDTO) {
        final Page<OumMenuPO> oumMenuPOPage = oumMenuService.findAllByDTO(oumMenuDTO);
        return OumMenuPOtoVOConverter.INSTANCE.toPage(oumMenuPOPage);
    }

    @Override
    @Operation(summary = "通过 id 查询")
    public OumMenuVO findById(final Long id) {
        return oumMenuService.findById(id)
                .map(OumMenuPOtoVOConverter.INSTANCE::toTarget)
                .orElse(null);
    }

    @Override
    @Operation(summary = "通过 id 删除")
    @Log(description = "menu delete",
            logType = LogTypeEnum.OPERATION,
            operationType = OperationTypeEnum.DELETE)
    public void deleteById(final Long id) {
        oumMenuService.deleteById(id);
    }

    @Override
    @Operation(summary = "新增")
    @Log(description = "menu save",
            logType = LogTypeEnum.OPERATION,
            operationType = OperationTypeEnum.INSERT)
    public Long save(final OumMenuDTO oumMenuDTO) {
        final OumMenuPO oumMenuPO = OumMenuDTOtoPOConverter.INSTANCE.toTarget(oumMenuDTO);
        return oumMenuService.save(oumMenuPO).getId();
    }
}
