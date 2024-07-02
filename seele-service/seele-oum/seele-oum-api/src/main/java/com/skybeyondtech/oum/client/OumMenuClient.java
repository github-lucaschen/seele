package com.skybeyondtech.oum.client;

import com.skybeyondtech.oum.api.OumMenuApi;
import com.skybeyondtech.oum.constant.OumConstant;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = OumConstant.BASE_SERVICE_NAME,
        path = OumConstant.BASE_PATH + "/menu")
public interface OumMenuClient extends OumMenuApi {
}
