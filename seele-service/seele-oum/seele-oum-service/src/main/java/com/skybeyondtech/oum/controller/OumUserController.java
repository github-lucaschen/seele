package com.skybeyondtech.oum.controller;

import com.skybeyondtech.base.enums.LogTypeEnum;
import com.skybeyondtech.base.enums.OperationTypeEnum;
import com.skybeyondtech.common.log.annotation.Log;
import com.skybeyondtech.oum.api.OumUserApi;
import com.skybeyondtech.oum.convert.OumUserDTOtoPOConverter;
import com.skybeyondtech.oum.convert.OumUserPOtoVOConverter;
import com.skybeyondtech.oum.domain.dto.OumUserDTO;
import com.skybeyondtech.oum.domain.po.OumUserPO;
import com.skybeyondtech.oum.domain.vo.OumUserVO;
import com.skybeyondtech.oum.service.OumUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Tag(name = "用户管理")
@RequiredArgsConstructor
public class OumUserController implements OumUserApi {

    private final OumUserService oumUserService;

    @Override
    @Operation(summary = "分页查询数据")
    public Page<OumUserVO> page(@ParameterObject final OumUserDTO oumUserDTO) {
        final Page<OumUserPO> oumUserPOPage = oumUserService.findAllByDTO(oumUserDTO);
        return OumUserPOtoVOConverter.INSTANCE.toPage(oumUserPOPage);
    }

    @Override
    @Operation(summary = "通过 id 查询")
    public OumUserVO findById(final Long id) {
        return oumUserService.findById(id)
                .map(OumUserPOtoVOConverter.INSTANCE::toTarget)
                .orElse(null);
    }

    @Override
    @Operation(summary = "通过 id 删除")
    @Log(description = "user delete",
            logType = LogTypeEnum.OPERATION,
            operationType = OperationTypeEnum.DELETE)
    public void deleteById(final Long id) {
        oumUserService.deleteById(id);
    }

    @Override
    @Operation(summary = "新增")
    @Log(description = "user save",
            logType = LogTypeEnum.OPERATION,
            operationType = OperationTypeEnum.INSERT)
    public Long save(final OumUserDTO oumUserDTO) {
        final OumUserPO oumUserPO = OumUserDTOtoPOConverter.INSTANCE.toTarget(oumUserDTO);
        return oumUserService.save(oumUserPO).getId();
    }

}
