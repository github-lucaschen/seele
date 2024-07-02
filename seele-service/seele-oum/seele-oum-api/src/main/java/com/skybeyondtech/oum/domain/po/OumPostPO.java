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

@Getter
@Setter
@Entity
@Table(name = "oum_post", schema = "seele")
@ToString
@SuperBuilder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@Schema(description = "岗位")
@Where(clause = "is_deleted = 0")
@SQLDelete(sql = "update oum_post set is_deleted = 1 where id = ?")
public class OumPostPO extends PO {
    @Size(max = 64)
    @NotNull
    @Column(name = "post_code", nullable = false, length = 64)
    @Schema(description = "岗位编码")
    private String postCode;

    @Size(max = 50)
    @NotNull
    @Column(name = "post_name", nullable = false, length = 50)
    @Schema(description = "岗位名称")
    private String postName;

    @Column(name = "order_number", columnDefinition = "int UNSIGNED not null")
    @Schema(description = "显示顺序")
    private Long orderNumber;

    @Column(name = "is_enabled", columnDefinition = "tinyint UNSIGNED")
    @Schema(description = "状态")
    private Integer enabled;

}