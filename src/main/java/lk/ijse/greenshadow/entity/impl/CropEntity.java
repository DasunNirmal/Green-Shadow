package lk.ijse.greenshadow.entity.impl;

import jakarta.persistence.*;
import lk.ijse.greenshadow.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Crops")
public class CropEntity implements SuperEntity {
    @Id
    private String crop_code;
    private String common_name;
    private String scientific_name;
    private String category;
    private String img;
    private String season;
    @ManyToOne
    @JoinColumn(name = "field_code",nullable = false)
    private FieldEntity field;
    @OneToMany(mappedBy = "crops")
    private List<CropMonitoringDetails> cropMonitoringDetails;
}
