package com.skybeyondtech.oum.api;

import com.skybeyondtech.oum.domain.dto.OumUserPostDTO;
import com.skybeyondtech.oum.domain.vo.OumUserPostVO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface OumUserPostApi {

    @GetMapping("/{id}")
    OumUserPostVO findById(@PathVariable(value = "id") final Long id);

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable(value = "id") final Long id);

    @PostMapping
    Long save(@RequestBody final OumUserPostDTO oumUserPostDTO);
}
