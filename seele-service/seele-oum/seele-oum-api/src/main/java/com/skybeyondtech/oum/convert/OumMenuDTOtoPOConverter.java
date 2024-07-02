package com.skybeyondtech.oum.convert;

import com.skybeyondtech.common.jpa.convert.Converter;
import com.skybeyondtech.oum.domain.dto.OumMenuDTO;
import com.skybeyondtech.oum.domain.po.OumMenuPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OumMenuDTOtoPOConverter extends Converter<OumMenuDTO,
        OumMenuPO> {
    OumMenuDTOtoPOConverter INSTANCE = Mappers.getMapper(OumMenuDTOtoPOConverter.class);
}
