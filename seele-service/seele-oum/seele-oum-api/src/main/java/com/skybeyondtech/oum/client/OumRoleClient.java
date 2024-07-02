package com.skybeyondtech.oum.client;

import com.skybeyondtech.oum.api.OumRoleApi;
import com.skybeyondtech.oum.constant.OumConstant;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = OumConstant.BASE_SERVICE_NAME,
        path = OumConstant.BASE_PATH + "/role")
public interface OumRoleClient extends OumRoleApi {
}
