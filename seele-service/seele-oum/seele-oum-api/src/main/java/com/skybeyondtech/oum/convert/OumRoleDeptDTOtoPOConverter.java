package com.skybeyondtech.oum.convert;

import com.skybeyondtech.common.jpa.convert.Converter;
import com.skybeyondtech.oum.domain.dto.OumRoleDeptDTO;
import com.skybeyondtech.oum.domain.po.OumRoleDeptPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OumRoleDeptDTOtoPOConverter extends Converter<OumRoleDeptDTO,
        OumRoleDeptPO> {
    OumRoleDeptDTOtoPOConverter INSTANCE = Mappers.getMapper(OumRoleDeptDTOtoPOConverter.class);
}
