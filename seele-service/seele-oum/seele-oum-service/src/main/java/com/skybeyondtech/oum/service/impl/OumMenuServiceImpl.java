package com.skybeyondtech.oum.service.impl;

import com.google.common.collect.Lists;
import com.skybeyondtech.common.jpa.repository.Repository;
import com.skybeyondtech.common.jpa.service.impl.ServiceImpl;
import com.skybeyondtech.oum.domain.dto.OumMenuDTO;
import com.skybeyondtech.oum.domain.po.OumMenuPO;
import com.skybeyondtech.oum.repository.OumMenuRepository;
import com.skybeyondtech.oum.service.OumMenuService;
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
public class OumMenuServiceImpl extends ServiceImpl<OumMenuPO, Long> implements OumMenuService {

    private final OumMenuRepository oumMenuRepository;

    @Override
    public Repository<OumMenuPO, Long> repository() {
        return oumMenuRepository;
    }

    @Override
    public Page<OumMenuPO> findAllByDTO(final OumMenuDTO oumMenuDTO) {
        final Pageable pageable = PageRequest.of(oumMenuDTO.getPage(), oumMenuDTO.getSize());
        final Specification<OumMenuPO> specification = (root, query, criteriaBuilder) -> {
            final List<Predicate> predicateList = Lists.newArrayList();
            final String menuName = oumMenuDTO.getMenuName();

            if (StringUtils.isNoneBlank(menuName)) {
                predicateList.add(criteriaBuilder.like(root.get("menuName"), "%" + menuName + "%"));
            }
            final Long parentId = oumMenuDTO.getParentId();
            if (Objects.nonNull(parentId)) {
                predicateList.add(criteriaBuilder.equal(root.get("parentId"), parentId));
            }
            final Integer enabled = oumMenuDTO.getEnabled();
            if (Objects.nonNull(enabled)) {
                predicateList.add(criteriaBuilder.equal(root.get("enabled"), enabled));
            }
            if (CollectionUtils.isNotEmpty(predicateList)) {
                return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
            }
            return null;
        };
        return oumMenuRepository.findAll(specification, pageable);
    }
}