package com.skybeyondtech.base.controller;

import com.skybeyondtech.base.domain.dto.CacheDTO;
import com.skybeyondtech.common.redis.service.RedisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
@Tag(name = "缓存服务查询")
@RequiredArgsConstructor
public class RedisController {

    private final RedisService<String> redisService;

    @GetMapping("/string/key/{key}")
    @Operation(summary = "[String] 根据 key 获取值")
    public String stringGet(@PathVariable final String key) {
        return redisService.stringGet(key);
    }

    @PostMapping("/string")
    @Operation(summary = "[String] 保存 key 和 value")
    public void stringSet(@RequestBody final CacheDTO cacheDTO) {
        redisService.stringSet(cacheDTO.getKey(), cacheDTO.getValue());
    }
}
