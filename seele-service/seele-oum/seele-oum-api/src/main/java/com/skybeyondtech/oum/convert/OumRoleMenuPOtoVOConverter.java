package com.skybeyondtech.oum.convert;

import com.skybeyondtech.common.jpa.convert.Converter;
import com.skybeyondtech.oum.domain.po.OumRoleMenuPO;
import com.skybeyondtech.oum.domain.vo.OumRoleMenuVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OumRoleMenuPOtoVOConverter extends Converter<OumRoleMenuPO,
        OumRoleMenuVO> {
    OumRoleMenuPOtoVOConverter INSTANCE = Mappers.getMapper(OumRoleMenuPOtoVOConverter.class);
}
