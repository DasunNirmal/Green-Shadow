package lk.ijse.greenshadow.entity;

import jakarta.persistence.*;
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
    private String role;
    private String first_name;
    private String phone_no;
    private String email;
    @ManyToOne
    @JoinColumn(name = "staff_id",nullable = false)
    private StaffEntity staff;
}
