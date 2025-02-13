package com.skybeyondtech.oum.domain.po;

import com.skybeyondtech.common.jpa.domain.po.PO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "oum_user", schema = "seele")
@ToString
@SuperBuilder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@Schema(description = "人员")
@Where(clause = "is_deleted = 0")
@SQLDelete(sql = "update oum_user set is_deleted = 1 where id = ?")
public class OumUserPO extends PO {

    @Column(name = "department_id")
    @Schema(description = "部门ID")
    private Long departmentId;

    @Size(max = 30)
    @NotNull
    @Column(name = "user_code", nullable = false, length = 30)
    @Schema(description = "用户账号")
    private String userCode;

    @Size(max = 30)
    @NotNull
    @Column(name = "nick_name", nullable = false, length = 30)
    @Schema(description = "用户昵称")
    private String nickName;

    @Size(max = 2)
    @Column(name = "type", length = 2)
    @Schema(description = "用户类型")
    private String type;

    @Size(max = 50)
    @Column(name = "email", length = 50)
    @Schema(description = "用户邮箱")
    private String email;

    @Size(max = 11)
    @Column(name = "phone", length = 11)
    @Schema(description = "手机号码")
    private String phone;

    @Column(name = "gender")
    @Schema(description = "用户性别")
    private Character gender;

    @Size(max = 100)
    @Column(name = "avatar", length = 100)
    @Schema(description = "头像地址")
    private String avatar;

    @Size(max = 100)
    @Column(name = "password", length = 100)
    @Schema(description = "密码")
    private String password;

    @Column(name = "is_enabled", columnDefinition = "tinyint UNSIGNED")
    @Schema(description = "部门状态")
    private Integer enabled;

    @Size(max = 128)
    @Column(name = "login_ip", length = 128)
    @Schema(description = "最后登录IP")
    private String loginIp;

    @Column(name = "login_date")
    @Schema(description = "最后登录时间")
    private Instant loginDate;

    @Size(max = 500)
    @Column(name = "remark", length = 500)
    @Schema(description = "备注")
    private String remark;
}