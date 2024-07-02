package com.skybeyondtech.oum.controller;

import com.skybeyondtech.base.enums.LogTypeEnum;
import com.skybeyondtech.base.enums.OperationTypeEnum;
import com.skybeyondtech.common.log.annotation.Log;
import com.skybeyondtech.oum.api.OumUserPostApi;
import com.skybeyondtech.oum.convert.OumUserPostDTOtoPOConverter;
import com.skybeyondtech.oum.convert.OumUserPostPOtoVOConverter;
import com.skybeyondtech.oum.domain.dto.OumUserPostDTO;
import com.skybeyondtech.oum.domain.po.OumUserPostPO;
import com.skybeyondtech.oum.domain.vo.OumUserPostVO;
import com.skybeyondtech.oum.service.OumUserPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-post")
@Tag(name = "用户岗位管理")
@RequiredArgsConstructor
public class OumUserPostController implements OumUserPostApi {

    private final OumUserPostService oumUserPostService;

    @Override
    @Operation(summary = "通过 id 查询")
    public OumUserPostVO findById(final Long id) {
        return oumUserPostService.findById(id)
                .map(OumUserPostPOtoVOConverter.INSTANCE::toTarget)
                .orElse(null);
    }

    @Override
    @Operation(summary = "通过 id 删除")
    @Log(description = "user-post delete",
            logType = LogTypeEnum.OPERATION,
            operationType = OperationTypeEnum.DELETE)
    public void deleteById(final Long id) {
        oumUserPostService.deleteById(id);
    }

    @Override
    @Operation(summary = "新增")
    @Log(description = "user-post save",
            logType = LogTypeEnum.OPERATION,
            operationType = OperationTypeEnum.INSERT)
    public Long save(final OumUserPostDTO oumUserPostDTO) {
        final OumUserPostPO oumUserPostPO = OumUserPostDTOtoPOConverter.INSTANCE.toTarget(oumUserPostDTO);
        return oumUserPostService.save(oumUserPostPO).getId();
    }

}
