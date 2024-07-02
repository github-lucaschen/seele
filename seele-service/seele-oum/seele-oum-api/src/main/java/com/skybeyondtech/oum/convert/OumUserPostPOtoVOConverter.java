package com.skybeyondtech.oum.convert;

import com.skybeyondtech.common.jpa.convert.Converter;
import com.skybeyondtech.oum.domain.po.OumUserPostPO;
import com.skybeyondtech.oum.domain.vo.OumUserPostVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OumUserPostPOtoVOConverter extends Converter<OumUserPostPO,
        OumUserPostVO> {
    OumUserPostPOtoVOConverter INSTANCE = Mappers.getMapper(OumUserPostPOtoVOConverter.class);
}
