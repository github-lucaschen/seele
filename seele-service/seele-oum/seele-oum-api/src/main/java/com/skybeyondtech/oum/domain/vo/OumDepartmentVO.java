package com.skybeyondtech.oum.domain.vo;

import com.skybeyondtech.common.jpa.domain.vo.VO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OumDepartmentVO extends VO {
    @Schema(description = "父部门id")
    private Long parentId;


    @Schema(description = "祖级列表")
    private String ancestors;

    @Schema(description = "部门名称")
    private String departmentName;

    @Schema(description = "显示顺序")
    private Integer orderNumber;

    @Schema(description = "负责人")
    private String leader;

    @Schema(description = "联系电话")
    private String phone;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "部门状态")
    private Integer enabled;
}