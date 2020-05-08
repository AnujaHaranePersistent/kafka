package com.inventories.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Entity
@Table(name = "brand")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BrandEntity {
    @Id
    @Column(name = "id")
    @ApiModelProperty(notes = "The database generated brand ID")
    private int id;
    @Column(name = "manufacturer_id")
    @NotEmpty(message = "Please provide manufacturer id")
    @ApiModelProperty(notes = "The application specific manufacturer ID")
    private String manufacturerId;
    @ApiModelProperty(notes = "The Code of Brand ")
    @Column(name = "brand_code")
    @NotEmpty(message = "Please provide a brand code")
    private String brandCode;

    @Column(name = "brand_name")
    @ApiModelProperty(notes = "The name of brand")
    @NotEmpty(message = "Please provide a brand name")
    private String brandName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrandEntity that = (BrandEntity) o;
        return id == that.id &&
                Objects.equals(manufacturerId, that.manufacturerId) &&
                Objects.equals(brandCode, that.brandCode) &&
                Objects.equals(brandName, that.brandName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, manufacturerId, brandCode, brandName);
    }
}
