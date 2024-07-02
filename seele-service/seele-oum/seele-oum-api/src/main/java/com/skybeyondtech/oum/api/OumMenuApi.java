package com.skybeyondtech.oum.api;

import com.skybeyondtech.oum.domain.dto.OumMenuDTO;
import com.skybeyondtech.oum.domain.vo.OumMenuVO;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface OumMenuApi {
    @GetMapping("/page")
    Page<OumMenuVO> page(@ModelAttribute final OumMenuDTO oumMenuDTO);

    @GetMapping("/{id}")
    OumMenuVO findById(@PathVariable(value = "id") final Long id);

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable(value = "id") final Long id);

    @PostMapping
    Long save(@RequestBody final OumMenuDTO oumMenuDTO);
}
