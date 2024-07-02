package com.skybeyondtech.oum.convert;

import com.skybeyondtech.common.jpa.convert.Converter;
import com.skybeyondtech.oum.domain.po.OumPostPO;
import com.skybeyondtech.oum.domain.vo.OumPostVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OumPostPOtoVOConverter extends Converter<OumPostPO,
        OumPostVO> {
    OumPostPOtoVOConverter INSTANCE = Mappers.getMapper(OumPostPOtoVOConverter.class);
}
