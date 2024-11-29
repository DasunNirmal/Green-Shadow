package lk.ijse.greenshadow.entity.impl;

import jakarta.persistence.*;
import lk.ijse.greenshadow.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "vehicle")
public class VehicleEntity implements SuperEntity {
    @Id
    private String vehicle_code;
    private String license_plate;
    private String fuel_type;
    private String role;
    private String remarks;
    private String vehicle_category;
    private String status;
    private String first_name;
    private String phone_no;
    private String email;
    @ManyToOne
    @JoinColumn(name = "staff_id",nullable = false)
    private StaffEntity staff;
}
