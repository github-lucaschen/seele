package com.skybeyondtech.base.convert;

import com.skybeyondtech.base.domain.po.OperationLogPO;
import com.skybeyondtech.base.domain.vo.OperationLogVO;
import com.skybeyondtech.common.jpa.convert.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OperationLogPOtoVOConverter extends Converter<OperationLogPO, OperationLogVO> {
    OperationLogPOtoVOConverter INSTANCE = Mappers.getMapper(OperationLogPOtoVOConverter.class);
}
