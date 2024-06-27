package com.skybeyondtech.common.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface Repository<T, ID> extends JpaRepository<T, ID>,
        JpaSpecificationExecutor<T> {
}
