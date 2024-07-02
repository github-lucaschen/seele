package com.skybeyondtech.oum.convert;

import com.skybeyondtech.common.jpa.convert.Converter;
import com.skybeyondtech.oum.domain.po.OumUserPO;
import com.skybeyondtech.oum.domain.vo.OumUserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OumUserPOtoVOConverter extends Converter<OumUserPO,
        OumUserVO> {
    OumUserPOtoVOConverter INSTANCE = Mappers.getMapper(OumUserPOtoVOConverter.class);
}
