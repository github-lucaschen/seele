package com.skybeyondtech.oum.convert;

import com.skybeyondtech.common.jpa.convert.Converter;
import com.skybeyondtech.oum.domain.dto.OumRoleMenuDTO;
import com.skybeyondtech.oum.domain.po.OumRoleMenuPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OumRoleMenuDTOtoPOConverter extends Converter<OumRoleMenuDTO,
        OumRoleMenuPO> {
    OumRoleMenuDTOtoPOConverter INSTANCE = Mappers.getMapper(OumRoleMenuDTOtoPOConverter.class);
}
