package com.skybeyondtech.base.convert;

import com.skybeyondtech.base.domain.dto.OperationLogDTO;
import com.skybeyondtech.base.domain.po.OperationLogPO;
import com.skybeyondtech.common.jpa.convert.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OperationLogDTOtoPOConverter extends Converter<OperationLogDTO, OperationLogPO> {
    OperationLogDTOtoPOConverter INSTANCE = Mappers.getMapper(OperationLogDTOtoPOConverter.class);
}
