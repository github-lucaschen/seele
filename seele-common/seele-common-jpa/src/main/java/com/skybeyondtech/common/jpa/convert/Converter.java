package com.skybeyondtech.common.jpa.convert;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

public interface Converter<S, T> {
    T toTarget(final S s);

    S toSource(final T t);

    List<T> toTargetList(final List<S> ss);

    List<S> toSourceList(final List<T> ts);

    default Page<T> toPage(Page<S> pp) {
        final List<T> vs = pp.getContent().stream()
                .map(this::toTarget)
                .collect(Collectors.toList());
        return new PageImpl<>(vs, pp.getPageable(), pp.getTotalElements());
    }
}