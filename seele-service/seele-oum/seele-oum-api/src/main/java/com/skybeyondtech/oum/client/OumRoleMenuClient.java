package com.skybeyondtech.oum.client;

import com.skybeyondtech.oum.api.OumRoleDeptApi;
import com.skybeyondtech.oum.api.OumRoleMenuApi;
import com.skybeyondtech.oum.constant.OumConstant;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = OumConstant.BASE_SERVICE_NAME,
        path = OumConstant.BASE_PATH + "/role-menu")
public interface OumRoleMenuClient extends OumRoleMenuApi {
}
