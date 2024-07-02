package com.skybeyondtech.oum.convert;

import com.skybeyondtech.common.jpa.convert.Converter;
import com.skybeyondtech.oum.domain.dto.OumRoleDTO;
import com.skybeyondtech.oum.domain.po.OumRolePO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OumRoleDTOtoPOConverter extends Converter<OumRoleDTO,
        OumRolePO> {
    OumRoleDTOtoPOConverter INSTANCE = Mappers.getMapper(OumRoleDTOtoPOConverter.class);
}
