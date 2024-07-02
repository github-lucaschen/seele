package com.skybeyondtech.oum.api;

import com.skybeyondtech.oum.domain.dto.OumUserDTO;
import com.skybeyondtech.oum.domain.vo.OumUserVO;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface OumUserApi {
    @GetMapping("/page")
    Page<OumUserVO> page(@ModelAttribute final OumUserDTO oumUserDTO);

    @GetMapping("/{id}")
    OumUserVO findById(@PathVariable(value = "id") final Long id);

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable(value = "id") final Long id);

    @PostMapping
    Long save(@RequestBody final OumUserDTO oumUserDTO);
}
