package com.skybeyondtech.oum.service.impl;

import com.google.common.collect.Lists;
import com.skybeyondtech.common.jpa.repository.Repository;
import com.skybeyondtech.common.jpa.service.impl.ServiceImpl;
import com.skybeyondtech.oum.domain.dto.OumUserDTO;
import com.skybeyondtech.oum.domain.po.OumUserPO;
import com.skybeyondtech.oum.repository.OumUserRepository;
import com.skybeyondtech.oum.service.OumUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class OumUserServiceImpl extends ServiceImpl<OumUserPO, Long> implements OumUserService {

    private final OumUserRepository oumUserRepository;

    @Override
    public Repository<OumUserPO, Long> repository() {
        return oumUserRepository;
    }

    @Override
    public Page<OumUserPO> findAllByDTO(final OumUserDTO oumUserDTO) {
        final Pageable pageable = PageRequest.of(oumUserDTO.getPage(), oumUserDTO.getSize());
        final Specification<OumUserPO> specification = (root, query, criteriaBuilder) -> {
            final List<Predicate> predicateList = Lists.newArrayList();

            final String userCode = oumUserDTO.getUserCode();
            if (StringUtils.isNoneBlank(userCode)) {
                predicateList.add(criteriaBuilder.equal(root.get("userCode"), userCode));
            }
            final String nickName = oumUserDTO.getNickName();
            if (StringUtils.isNoneBlank(nickName)) {
                predicateList.add(criteriaBuilder.like(root.get("nickName"), "%" + nickName + "%"));
            }
            final Integer enabled = oumUserDTO.getEnabled();
            if (Objects.nonNull(enabled)) {
                predicateList.add(criteriaBuilder.equal(root.get("enabled"), enabled));
            }
            if (CollectionUtils.isNotEmpty(predicateList)) {
                return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
            }
            return null;
        };
        return oumUserRepository.findAll(specification, pageable);
    }
}