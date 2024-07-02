package com.skybeyondtech.oum.convert;

import com.skybeyondtech.common.jpa.convert.Converter;
import com.skybeyondtech.oum.domain.po.OumRolePO;
import com.skybeyondtech.oum.domain.vo.OumRoleVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OumRolePOtoVOConverter extends Converter<OumRolePO,
        OumRoleVO> {
    OumRolePOtoVOConverter INSTANCE = Mappers.getMapper(OumRolePOtoVOConverter.class);
}
