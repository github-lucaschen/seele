package com.skybeyondtech.oum.convert;

import com.skybeyondtech.common.jpa.convert.Converter;
import com.skybeyondtech.oum.domain.dto.OumDepartmentDTO;
import com.skybeyondtech.oum.domain.po.OumDepartmentPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OumDepartmentDTOtoPOConverter extends Converter<OumDepartmentDTO,
        OumDepartmentPO> {
    OumDepartmentDTOtoPOConverter INSTANCE = Mappers.getMapper(OumDepartmentDTOtoPOConverter.class);
}
