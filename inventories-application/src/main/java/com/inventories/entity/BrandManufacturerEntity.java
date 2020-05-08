package com.inventories.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Entity
@Table(name = "brand_manufacturer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BrandManufacturerEntity {

    @Id
    @Column(name = "id")
    @ApiModelProperty(notes = "The database generated manufacturer ID")
    private int id;


    @Column(name = "manufacturer_name")
    @ApiModelProperty(notes = "The name of manufacturer")
    @NotEmpty(message = "Please provide a manufacturer name")
    private String manufacturerName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrandManufacturerEntity that = (BrandManufacturerEntity) o;
        return id == that.id &&
                Objects.equals(manufacturerName, that.manufacturerName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, manufacturerName);
    }
}
