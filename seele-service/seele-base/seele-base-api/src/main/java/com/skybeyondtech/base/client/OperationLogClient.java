package com.skybeyondtech.base.client;

import com.skybeyondtech.base.api.OperationLogApi;
import com.skybeyondtech.base.constant.BaseConstant;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = BaseConstant.BASE_SERVICE_NAME,
        path = BaseConstant.BASE_PATH + "/operation-log")
public interface OperationLogClient extends OperationLogApi {
}