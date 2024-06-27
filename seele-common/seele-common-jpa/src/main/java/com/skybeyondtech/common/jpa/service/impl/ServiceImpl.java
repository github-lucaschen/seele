package com.skybeyondtech.common.jpa.service.impl;

import com.skybeyondtech.common.jpa.repository.Repository;
import com.skybeyondtech.common.jpa.service.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public abstract class ServiceImpl<T, ID> implements Service<T, ID> {

    public abstract Repository<T, ID> repository();

    @Override
    @Transactional(rollbackFor = Exception.class)
    public <S extends T> S save(final S entity) {
        return repository().save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public <S extends T> Iterable<S> saveAll(final Iterable<S> entities) {
        return repository().saveAll(entities);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public <S extends T> S saveAndFlush(final S entity) {
        return repository().saveAndFlush(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public <S extends T> List<S> saveAllAndFlush(final Iterable<S> entities) {
        return repository().saveAllAndFlush(entities);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(final ID id) {
        repository().deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAllByIdInBatch(final Iterable<ID> ids) {
        repository().deleteAllByIdInBatch(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(final ID id) {
        return repository().existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<T> findById(final ID id) {
        return repository().findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<T> findAllById(final Iterable<ID> ids) {
        return repository().findAllById(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<T> findOne(final Specification<T> spec) {
        return repository().findOne(spec);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll(final Specification<T> spec) {
        return repository().findAll(spec);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<T> findAll(final Specification<T> spec, final Pageable pageable) {
        return repository().findAll(spec, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll(final Specification<T> spec, final Sort sort) {
        return repository().findAll(spec, sort);
    }

    @Override
    @Transactional(readOnly = true)
    public long count(final Specification<T> spec) {
        return repository().count(spec);
    }
}
