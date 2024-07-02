package com.skybeyondtech.oum.client;

import com.skybeyondtech.oum.api.OumDepartmentApi;
import com.skybeyondtech.oum.constant.OumConstant;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = OumConstant.BASE_SERVICE_NAME,
        path = OumConstant.BASE_PATH + "/department")
public interface OumDepartmentClient extends OumDepartmentApi {
}
