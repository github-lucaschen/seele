package com.skybeyondtech.oum.convert;

import com.skybeyondtech.common.jpa.convert.Converter;
import com.skybeyondtech.oum.domain.po.OumUserRolePO;
import com.skybeyondtech.oum.domain.vo.OumUserRoleVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OumUserRolePOtoVOConverter extends Converter<OumUserRolePO,
        OumUserRoleVO> {
    OumUserRolePOtoVOConverter INSTANCE = Mappers.getMapper(OumUserRolePOtoVOConverter.class);
}
