package com.skybeyondtech.oum.api;

import com.skybeyondtech.oum.domain.dto.OumUserRoleDTO;
import com.skybeyondtech.oum.domain.vo.OumUserRoleVO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface OumUserRoleApi {

    @GetMapping("/{id}")
    OumUserRoleVO findById(@PathVariable(value = "id") final Long id);

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable(value = "id") final Long id);

    @PostMapping
    Long save(@RequestBody final OumUserRoleDTO oumUserRoleDTO);
}
