package com.skybeyondtech.oum.convert;

import com.skybeyondtech.common.jpa.convert.Converter;
import com.skybeyondtech.oum.domain.dto.OumUserDTO;
import com.skybeyondtech.oum.domain.po.OumUserPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OumUserDTOtoPOConverter extends Converter<OumUserDTO,
        OumUserPO> {
    OumUserDTOtoPOConverter INSTANCE = Mappers.getMapper(OumUserDTOtoPOConverter.class);
}
