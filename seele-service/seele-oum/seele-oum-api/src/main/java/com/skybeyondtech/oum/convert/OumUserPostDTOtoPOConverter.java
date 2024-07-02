package com.skybeyondtech.oum.convert;

import com.skybeyondtech.common.jpa.convert.Converter;
import com.skybeyondtech.oum.domain.dto.OumUserPostDTO;
import com.skybeyondtech.oum.domain.po.OumUserPostPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OumUserPostDTOtoPOConverter extends Converter<OumUserPostDTO,
        OumUserPostPO> {
    OumUserPostDTOtoPOConverter INSTANCE = Mappers.getMapper(OumUserPostDTOtoPOConverter.class);
}
