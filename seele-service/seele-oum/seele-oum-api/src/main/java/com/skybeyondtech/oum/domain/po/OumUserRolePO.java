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

@Getter
@Setter
@Entity
@Table(name = "oum_user_role", schema = "seele")
@ToString
@SuperBuilder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@Schema(description = "用户角色")
@Where(clause = "is_deleted = 0")
@SQLDelete(sql = "update oum_user_role set is_deleted = 1 where id = ?")
public class OumUserRolePO extends PO {
    @NotNull
    @Column(name = "user_id", nullable = false)
    @Schema(description = "用户id")
    private Long userId;

    @NotNull
    @Column(name = "role_id", nullable = false)
    @Schema(description = "角色id")
    private Long roleId;

}