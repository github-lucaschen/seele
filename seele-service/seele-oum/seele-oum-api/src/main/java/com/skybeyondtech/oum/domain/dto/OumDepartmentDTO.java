package com.skybeyondtech.oum.domain.dto;

import com.skybeyondtech.common.jpa.domain.dto.DTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Size;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OumDepartmentDTO extends DTO {
    @Schema(description = "父部门id")
    private Long parentId;

    @Size(max = 50)
    @Schema(description = "祖级列表")
    private String ancestors;

    @Size(max = 30)
    @Schema(description = "部门名称")
    private String departmentName;

    @Schema(description = "显示顺序")
    private Integer orderNumber;

    @Size(max = 20)
    @Schema(description = "负责人")
    private String leader;

    @Size(max = 11)
    @Schema(description = "联系电话")
    private String phone;

    @Size(max = 50)
    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "部门状态")
    private Integer enabled;

}