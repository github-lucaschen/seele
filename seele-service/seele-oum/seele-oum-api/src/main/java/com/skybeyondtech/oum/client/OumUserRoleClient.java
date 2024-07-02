package com.skybeyondtech.oum.client;

import com.skybeyondtech.oum.api.OumUserRoleApi;
import com.skybeyondtech.oum.constant.OumConstant;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = OumConstant.BASE_SERVICE_NAME,
        path = OumConstant.BASE_PATH + "/user-role")
public interface OumUserRoleClient extends OumUserRoleApi {
}
