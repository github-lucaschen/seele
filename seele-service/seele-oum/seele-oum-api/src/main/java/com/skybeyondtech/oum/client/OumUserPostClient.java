package com.skybeyondtech.oum.client;

import com.skybeyondtech.oum.api.OumUserPostApi;
import com.skybeyondtech.oum.constant.OumConstant;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = OumConstant.BASE_SERVICE_NAME,
        path = OumConstant.BASE_PATH + "/user-post")
public interface OumUserPostClient extends OumUserPostApi {
}
