package com.skybeyondtech.oum.convert;

import com.skybeyondtech.common.jpa.convert.Converter;
import com.skybeyondtech.oum.domain.dto.OumPostDTO;
import com.skybeyondtech.oum.domain.po.OumPostPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OumPostDTOtoPOConverter extends Converter<OumPostDTO,
        OumPostPO> {
    OumPostDTOtoPOConverter INSTANCE = Mappers.getMapper(OumPostDTOtoPOConverter.class);
}
