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
@Table(name = "field_staff_details")
public class FieldStaffDetailsEntity implements SuperEntity {
    @Id
    private String details_id;
    private String first_name;
    private String email;
    private String phone_no;
    private String role;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "staff_id",referencedColumnName = "staff_id")
    private StaffEntity staff;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "field_code",referencedColumnName = "field_code")
    private FieldEntity field;
}
