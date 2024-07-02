package com.skybeyondtech.oum.convert;

import com.skybeyondtech.common.jpa.convert.Converter;
import com.skybeyondtech.oum.domain.po.OumRoleDeptPO;
import com.skybeyondtech.oum.domain.vo.OumRoleDeptVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OumRoleDeptPOtoVOConverter extends Converter<OumRoleDeptPO,
        OumRoleDeptVO> {
    OumRoleDeptPOtoVOConverter INSTANCE = Mappers.getMapper(OumRoleDeptPOtoVOConverter.class);
}
