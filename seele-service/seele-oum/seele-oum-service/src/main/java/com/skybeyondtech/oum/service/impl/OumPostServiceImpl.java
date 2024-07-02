package com.skybeyondtech.oum.service.impl;

import com.google.common.collect.Lists;
import com.skybeyondtech.common.jpa.repository.Repository;
import com.skybeyondtech.common.jpa.service.impl.ServiceImpl;
import com.skybeyondtech.oum.domain.dto.OumPostDTO;
import com.skybeyondtech.oum.domain.po.OumPostPO;
import com.skybeyondtech.oum.repository.OumPostRepository;
import com.skybeyondtech.oum.service.OumPostService;
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
public class OumPostServiceImpl extends ServiceImpl<OumPostPO, Long> implements OumPostService {

    private final OumPostRepository oumPostRepository;

    @Override
    public Repository<OumPostPO, Long> repository() {
        return oumPostRepository;
    }

    @Override
    public Page<OumPostPO> findAllByDTO(final OumPostDTO oumPostDTO) {
        final Pageable pageable = PageRequest.of(oumPostDTO.getPage(), oumPostDTO.getSize());
        final Specification<OumPostPO> specification = (root, query, criteriaBuilder) -> {
            final List<Predicate> predicateList = Lists.newArrayList();
            final String postCode = oumPostDTO.getPostCode();
            if (StringUtils.isNoneBlank(postCode)) {
                predicateList.add(criteriaBuilder.equal(root.get("postCode"), postCode));
            }
            final String postName = oumPostDTO.getPostName();
            if (StringUtils.isNoneBlank(postName)) {
                predicateList.add(criteriaBuilder.like(root.get("postName"), "%" + postCode + "%"));
            }
            final Integer enabled = oumPostDTO.getEnabled();
            if (Objects.nonNull(enabled)) {
                predicateList.add(criteriaBuilder.equal(root.get("enabled"), enabled));
            }
            if (CollectionUtils.isNotEmpty(predicateList)) {
                return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
            }
            return null;
        };
        return oumPostRepository.findAll(specification, pageable);
    }
}