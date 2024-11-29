package lk.ijse.greenshadow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "field")
public class FieldEntity implements SuperEntity {
    @Id
    private String field_code;
    private String field_name;
    private Point field_location;
    private double extent_size;
    @Column(columnDefinition = "LONGTEXT")
    private String img_01;
    @Column(columnDefinition = "LONGTEXT")
    private String img_02;
    @OneToMany(mappedBy = "field")
    private List<CropEntity> crops;
    @OneToMany(mappedBy = "field")
    private List<EquipmentEntity> equipment;
    @OneToMany(mappedBy = "field")
    private List<FieldStaffDetailsEntity> fieldStaffDetails;
    @OneToMany(mappedBy = "field")
    private List<FieldMonitoringDetails> fieldMonitoringDetails;
}
