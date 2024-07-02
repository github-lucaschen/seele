package com.skybeyondtech.oum.api;

import com.skybeyondtech.oum.domain.dto.OumPostDTO;
import com.skybeyondtech.oum.domain.vo.OumPostVO;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface OumPostApi {
    @GetMapping("/page")
    Page<OumPostVO> page(@ModelAttribute final OumPostDTO oumPostDTO);

    @GetMapping("/{id}")
    OumPostVO findById(@PathVariable(value = "id") final Long id);

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable(value = "id") final Long id);

    @PostMapping
    Long save(@RequestBody final OumPostDTO oumPostDTO);
}
