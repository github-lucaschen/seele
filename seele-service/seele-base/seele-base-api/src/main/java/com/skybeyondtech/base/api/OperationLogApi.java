package com.skybeyondtech.base.api;

import com.skybeyondtech.base.domain.dto.OperationLogDTO;
import com.skybeyondtech.base.domain.vo.OperationLogVO;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface OperationLogApi {

    @GetMapping("/page")
    Page<OperationLogVO> page(@ModelAttribute final OperationLogDTO operationLogDTO);

    @GetMapping("/{id}")
    OperationLogVO findById(@PathVariable(value = "id") final Long id);

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable(value = "id") final Long id);

    @PostMapping
    Long save(@RequestBody final OperationLogDTO operationLogDTO);
}