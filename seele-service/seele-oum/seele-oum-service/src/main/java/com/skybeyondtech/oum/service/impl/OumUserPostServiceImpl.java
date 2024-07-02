package com.skybeyondtech.oum.service.impl;

import com.skybeyondtech.common.jpa.repository.Repository;
import com.skybeyondtech.common.jpa.service.impl.ServiceImpl;
import com.skybeyondtech.oum.domain.po.OumUserPostPO;
import com.skybeyondtech.oum.repository.OumUserPostRepository;
import com.skybeyondtech.oum.service.OumUserPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OumUserPostServiceImpl extends ServiceImpl<OumUserPostPO, Long> implements OumUserPostService {

    private final OumUserPostRepository oumUserPostRepository;

    @Override
    public Repository<OumUserPostPO, Long> repository() {
        return oumUserPostRepository;
    }
}