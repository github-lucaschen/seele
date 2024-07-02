package com.skybeyondtech.oum.controller;

import com.skybeyondtech.base.enums.LogTypeEnum;
import com.skybeyondtech.base.enums.OperationTypeEnum;
import com.skybeyondtech.common.log.annotation.Log;
import com.skybeyondtech.oum.api.OumPostApi;
import com.skybeyondtech.oum.convert.OumPostDTOtoPOConverter;
import com.skybeyondtech.oum.convert.OumPostPOtoVOConverter;
import com.skybeyondtech.oum.domain.dto.OumPostDTO;
import com.skybeyondtech.oum.domain.po.OumPostPO;
import com.skybeyondtech.oum.domain.vo.OumPostVO;
import com.skybeyondtech.oum.service.OumPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
@Tag(name = "岗位管理")
@RequiredArgsConstructor
public class OumPostController implements OumPostApi {

    private final OumPostService oumPostService;

    @Override
    @Operation(summary = "分页查询数据")
    public Page<OumPostVO> page(@ParameterObject final OumPostDTO oumPostDTO) {
        final Page<OumPostPO> oumPostPOPage = oumPostService.findAllByDTO(oumPostDTO);
        return OumPostPOtoVOConverter.INSTANCE.toPage(oumPostPOPage);
    }

    @Override
    @Operation(summary = "通过 id 查询")
    public OumPostVO findById(final Long id) {
        return oumPostService.findById(id)
                .map(OumPostPOtoVOConverter.INSTANCE::toTarget)
                .orElse(null);
    }

    @Override
    @Operation(summary = "通过 id 删除")
    @Log(description = "post delete",
            logType = LogTypeEnum.OPERATION,
            operationType = OperationTypeEnum.DELETE)
    public void deleteById(final Long id) {
        oumPostService.deleteById(id);
    }

    @Override
    @Operation(summary = "新增")
    @Log(description = "post save",
            logType = LogTypeEnum.OPERATION,
            operationType = OperationTypeEnum.INSERT)
    public Long save(final OumPostDTO oumPostDTO) {
        final OumPostPO oumPostPO = OumPostDTOtoPOConverter.INSTANCE.toTarget(oumPostDTO);
        return oumPostService.save(oumPostPO).getId();
    }

}
