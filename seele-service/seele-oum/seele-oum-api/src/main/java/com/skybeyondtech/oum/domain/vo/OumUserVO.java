package com.skybeyondtech.oum.domain.vo;

import com.skybeyondtech.common.jpa.domain.vo.VO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class OumUserVO extends VO {

    @Schema(description = "部门ID")
    private Long departmentId;

    @Schema(description = "用户账号")
    private String userCode;

    @Schema(description = "用户昵称")
    private String nickName;

    @Schema(description = "用户类型")
    private String type;

    @Schema(description = "用户邮箱")
    private String email;

    @Schema(description = "手机号码")
    private String phone;

    @Schema(description = "用户性别")
    private Character gender;

    @Schema(description = "头像地址")
    private String avatar;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "部门状态")
    private Integer enabled;

    @Schema(description = "最后登录IP")
    private String loginIp;

    @Schema(description = "最后登录时间")
    private Instant loginDate;

    @Schema(description = "备注")
    private String remark;
}