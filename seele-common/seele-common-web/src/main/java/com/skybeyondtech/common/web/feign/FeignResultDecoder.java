package com.skybeyondtech.common.web.feign;

import com.skybeyondtech.common.core.domain.R;
import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import lombok.RequiredArgsConstructor;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

@RequiredArgsConstructor
public class FeignResultDecoder implements Decoder {

    private final Decoder decoder;

    @Override
    public Object decode(final Response response, final Type type)
            throws IOException, DecodeException, FeignException {
        final Method method = response.request().requestTemplate().methodMetadata().method();
        final boolean isResult = method.getReturnType() != R.class;
        if (isResult) {
            ParameterizedTypeImpl resultType = ParameterizedTypeImpl
                    .make(R.class, new Type[]{type}, null);
            R<?> result = (R<?>) this.decoder.decode(response, resultType);
            return result.getData();
        }
        return this.decoder.decode(response, type);
    }
}