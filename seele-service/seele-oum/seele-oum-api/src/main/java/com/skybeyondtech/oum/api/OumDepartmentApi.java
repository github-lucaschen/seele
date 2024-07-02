package com.skybeyondtech.oum.api;

import com.skybeyondtech.oum.domain.dto.OumDepartmentDTO;
import com.skybeyondtech.oum.domain.vo.OumDepartmentVO;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface OumDepartmentApi {
    @GetMapping("/page")
    Page<OumDepartmentVO> page(@ModelAttribute final OumDepartmentDTO oumDepartmentDTO);

    @GetMapping("/{id}")
    OumDepartmentVO findById(@PathVariable(value = "id") final Long id);

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable(value = "id") final Long id);

    @PostMapping
    Long save(@RequestBody final OumDepartmentDTO oumDepartmentDTO);
}
