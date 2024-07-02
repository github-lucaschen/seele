package com.skybeyondtech.oum.domain.dto;

import com.skybeyondtech.common.jpa.domain.dto.DTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OumUserDTO extends DTO {

    @Schema(description = "部门ID")
    private Long departmentId;

    @Size(max = 30)
    @Schema(description = "用户账号")
    private String userCode;

    @Size(max = 30)
    @Schema(description = "用户昵称")
    private String nickName;

    @Size(max = 2)
    @Schema(description = "用户类型")
    private String type;

    @Size(max = 50)
    @Schema(description = "用户邮箱")
    private String email;

    @Size(max = 11)
    @Schema(description = "手机号码")
    private String phone;

    @Schema(description = "用户性别")
    private Character gender;

    @Size(max = 100)
    @Schema(description = "头像地址")
    private String avatar;

    @Size(max = 100)
    @Schema(description = "密码")
    private String password;

    @Schema(description = "部门状态")
    private Integer enabled;

    @Size(max = 128)
    @Schema(description = "最后登录IP")
    private String loginIp;

    @Schema(description = "最后登录时间")
    private Instant loginDate;

    @Size(max = 500)
    @Schema(description = "备注")
    private String remark;
}