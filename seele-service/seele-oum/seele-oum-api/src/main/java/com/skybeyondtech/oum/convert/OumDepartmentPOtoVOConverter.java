package com.skybeyondtech.oum.convert;

import com.skybeyondtech.common.jpa.convert.Converter;
import com.skybeyondtech.oum.domain.po.OumDepartmentPO;
import com.skybeyondtech.oum.domain.vo.OumDepartmentVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OumDepartmentPOtoVOConverter extends Converter<OumDepartmentPO,
        OumDepartmentVO> {
    OumDepartmentPOtoVOConverter INSTANCE = Mappers.getMapper(OumDepartmentPOtoVOConverter.class);
}
