package com.skybeyondtech.base.service.impl;

import com.google.common.collect.Lists;
import com.skybeyondtech.base.domain.dto.OperationLogDTO;
import com.skybeyondtech.base.domain.po.OperationLogPO;
import com.skybeyondtech.base.repository.OperationLogRepository;
import com.skybeyondtech.base.service.OperationLogService;
import com.skybeyondtech.common.jpa.repository.Repository;
import com.skybeyondtech.common.jpa.service.impl.ServiceImpl;
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
public class OperationLogServiceImpl extends ServiceImpl<OperationLogPO, Long> implements OperationLogService {

    private final OperationLogRepository operationLogRepository;

    @Override
    public Repository<OperationLogPO, Long> repository() {
        return operationLogRepository;
    }

    @Override
    public Page<OperationLogPO> findAllByDTO(final OperationLogDTO operationLogDTO) {
        final Pageable pageable = PageRequest.of(operationLogDTO.getPage(), operationLogDTO.getSize());
        final Specification<OperationLogPO> specification = (root, query, criteriaBuilder) -> {
            final List<Predicate> predicateList = Lists.newArrayList();
            final String userCode = operationLogDTO.getUserCode();
            if (StringUtils.isNoneBlank(userCode)) {
                predicateList.add(criteriaBuilder.equal(root.get("userCode"), userCode));
            }
            final String clientCode = operationLogDTO.getClientCode();
            if (StringUtils.isNoneBlank(clientCode)) {
                predicateList.add(criteriaBuilder.equal(root.get("clientCode"), clientCode));
            }
            final String description = operationLogDTO.getDescription();
            if (StringUtils.isNoneBlank(description)) {
                predicateList.add(criteriaBuilder.like(root.get("description"), "%" + description + "%"));
            }
            final Integer logType = operationLogDTO.getLogType();
            if (Objects.nonNull(logType)) {
                predicateList.add(criteriaBuilder.equal(root.get("logType"), logType));
            }
            final Integer operationType = operationLogDTO.getOperationType();
            if (Objects.nonNull(operationType)) {
                predicateList.add(criteriaBuilder.equal(root.get("operationType"), operationType));
            }
            final String className = operationLogDTO.getClassName();
            if (StringUtils.isNoneBlank(className)) {
                predicateList.add(criteriaBuilder.like(root.get("className"), "%" + className + "%"));
            }
            final String method = operationLogDTO.getMethod();
            if (StringUtils.isNoneBlank(method)) {
                predicateList.add(criteriaBuilder.like(root.get("method"), "%" + method + "%"));
            }
            final String url = operationLogDTO.getUrl();
            if (StringUtils.isNoneBlank(url)) {
                predicateList.add(criteriaBuilder.like(root.get("url"), "%" + url + "%"));
            }
            final String httpMethod = operationLogDTO.getHttpMethod();
            if (StringUtils.isNoneBlank(httpMethod)) {
                predicateList.add(criteriaBuilder.equal(root.get("httpMethod"), httpMethod));
            }
            final Integer resultType = operationLogDTO.getResultType();
            if (Objects.nonNull(resultType)) {
                predicateList.add(criteriaBuilder.equal(root.get("resultType"), resultType));
            }
            if (CollectionUtils.isNotEmpty(predicateList)) {
                return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
            }
            return null;
        };
        return operationLogRepository.findAll(specification, pageable);
    }
}