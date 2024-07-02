package com.skybeyondtech.oum.service.impl;

import com.google.common.collect.Lists;
import com.skybeyondtech.common.jpa.repository.Repository;
import com.skybeyondtech.common.jpa.service.impl.ServiceImpl;
import com.skybeyondtech.oum.domain.dto.OumRoleDTO;
import com.skybeyondtech.oum.domain.po.OumRolePO;
import com.skybeyondtech.oum.repository.OumRoleRepository;
import com.skybeyondtech.oum.service.OumRoleService;
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
public class OumRoleServiceImpl extends ServiceImpl<OumRolePO, Long> implements OumRoleService {

    private final OumRoleRepository oumRoleRepository;

    @Override
    public Repository<OumRolePO, Long> repository() {
        return oumRoleRepository;
    }

    @Override
    public Page<OumRolePO> findAllByDTO(final OumRoleDTO oumRoleDTO) {
        final Pageable pageable = PageRequest.of(oumRoleDTO.getPage(), oumRoleDTO.getSize());
        final Specification<OumRolePO> specification = (root, query, criteriaBuilder) -> {
            final List<Predicate> predicateList = Lists.newArrayList();
            final String roleKey = oumRoleDTO.getRoleKey();
            if (StringUtils.isNoneBlank(roleKey)) {
                predicateList.add(criteriaBuilder.equal(root.get("roleKey"), roleKey));
            }
            final String roleName = oumRoleDTO.getRoleName();
            if (StringUtils.isNoneBlank(roleName)) {
                predicateList.add(criteriaBuilder.like(root.get("roleName"), "%" + roleName + "%"));
            }
            final Integer enabled = oumRoleDTO.getEnabled();
            if (Objects.nonNull(enabled)) {
                predicateList.add(criteriaBuilder.equal(root.get("enabled"), enabled));
            }
            if (CollectionUtils.isNotEmpty(predicateList)) {
                return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
            }
            return null;
        };
        return oumRoleRepository.findAll(specification, pageable);
    }
}