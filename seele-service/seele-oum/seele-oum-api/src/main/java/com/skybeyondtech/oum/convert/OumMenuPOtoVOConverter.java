package com.skybeyondtech.oum.convert;

import com.skybeyondtech.common.jpa.convert.Converter;
import com.skybeyondtech.oum.domain.po.OumMenuPO;
import com.skybeyondtech.oum.domain.vo.OumMenuVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OumMenuPOtoVOConverter extends Converter<OumMenuPO,
        OumMenuVO> {
    OumMenuPOtoVOConverter INSTANCE = Mappers.getMapper(OumMenuPOtoVOConverter.class);
}
