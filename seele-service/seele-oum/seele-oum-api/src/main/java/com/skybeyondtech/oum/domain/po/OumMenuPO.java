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
@Table(name = "oum_menu", schema = "seele")
@ToString
@SuperBuilder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@Schema(description = "菜单")
@Where(clause = "is_deleted = 0")
@SQLDelete(sql = "update oum_menu set is_deleted = 1 where id = ?")
public class OumMenuPO extends PO {
    @Size(max = 50)
    @NotNull
    @Column(name = "menu_name", nullable = false, length = 50)
    @Schema(description = "菜单名称")
    private String menuName;

    @Column(name = "parent_id")
    @Schema(description = "父菜单ID")
    private Long parentId;

    @Column(name = "order_number", columnDefinition = "int UNSIGNED")
    @Schema(description = "显示顺序")
    private Long orderNumber;

    @Size(max = 200)
    @Column(name = "path", length = 200)
    @Schema(description = "路由地址")
    private String path;

    @Size(max = 255)
    @Column(name = "component")
    @Schema(description = "组件路径")
    private String component;

    @Size(max = 255)
    @Column(name = "query")
    @Schema(description = "路由参数")
    private String query;

    @Column(name = "is_frame", columnDefinition = "tinyint UNSIGNED")
    @Schema(description = "是否为外链")
    private Integer frame;

    @Column(name = "is_cache", columnDefinition = "tinyint UNSIGNED")
    @Schema(description = "是否缓存")
    private Integer cache;

    @Column(name = "menu_type")
    @Schema(description = "菜单类型")
    private Character menuType;

    @Column(name = "is_visible", columnDefinition = "tinyint UNSIGNED")
    @Schema(description = "菜单状态")
    private Integer visible;

    @Size(max = 100)
    @Column(name = "perms", length = 100)
    @Schema(description = "权限标识")
    private String perms;

    @Size(max = 100)
    @Column(name = "icon", length = 100)
    @Schema(description = "菜单图标")
    private String icon;

    @Size(max = 500)
    @Column(name = "remark", length = 500)
    @Schema(description = "备注")
    private String remark;

    @Column(name = "is_enabled", columnDefinition = "tinyint UNSIGNED")
    @Schema(description = "状态")
    private Integer enabled;

}