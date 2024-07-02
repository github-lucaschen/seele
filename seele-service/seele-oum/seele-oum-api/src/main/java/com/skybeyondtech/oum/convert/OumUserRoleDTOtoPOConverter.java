package com.skybeyondtech.oum.convert;

import com.skybeyondtech.common.jpa.convert.Converter;
import com.skybeyondtech.oum.domain.dto.OumUserRoleDTO;
import com.skybeyondtech.oum.domain.po.OumUserRolePO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OumUserRoleDTOtoPOConverter extends Converter<OumUserRoleDTO,
        OumUserRolePO> {
    OumUserRoleDTOtoPOConverter INSTANCE = Mappers.getMapper(OumUserRoleDTOtoPOConverter.class);
}
