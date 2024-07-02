package com.skybeyondtech.oum.service.impl;

import com.google.common.collect.Lists;
import com.skybeyondtech.common.jpa.repository.Repository;
import com.skybeyondtech.common.jpa.service.impl.ServiceImpl;
import com.skybeyondtech.oum.domain.dto.OumDepartmentDTO;
import com.skybeyondtech.oum.domain.po.OumDepartmentPO;
import com.skybeyondtech.oum.repository.OumDepartmentRepository;
import com.skybeyondtech.oum.service.OumDepartmentService;
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
public class OumDepartmentServiceImpl extends ServiceImpl<OumDepartmentPO, Long> implements OumDepartmentService {

    private final OumDepartmentRepository oumDepartmentRepository;

    @Override
    public Repository<OumDepartmentPO, Long> repository() {
        return oumDepartmentRepository;
    }


    @Override
    public Page<OumDepartmentPO> findAllByDTO(final OumDepartmentDTO oumDepartmentDTO) {
        final Pageable pageable = PageRequest.of(oumDepartmentDTO.getPage(), oumDepartmentDTO.getSize());
        final Specification<OumDepartmentPO> specification = (root, query, criteriaBuilder) -> {
            final List<Predicate> predicateList = Lists.newArrayList();
            final Long parentId = oumDepartmentDTO.getParentId();
            if (Objects.nonNull(parentId)) {
                predicateList.add(criteriaBuilder.equal(root.get("parentId"), parentId));
            }
            final String departmentName = oumDepartmentDTO.getDepartmentName();
            if (StringUtils.isNoneBlank(departmentName)) {
                predicateList.add(criteriaBuilder.like(root.get("departmentName"), "%" + departmentName + "%"));
            }
            final Integer enabled = oumDepartmentDTO.getEnabled();
            if (Objects.nonNull(enabled)) {
                predicateList.add(criteriaBuilder.equal(root.get("enabled"), enabled));
            }
            if (CollectionUtils.isNotEmpty(predicateList)) {
                return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
            }
            return null;
        };
        return oumDepartmentRepository.findAll(specification, pageable);
    }
}