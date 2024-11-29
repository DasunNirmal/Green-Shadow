package lk.ijse.greenshadow.entity.impl;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lk.ijse.greenshadow.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "staff")
public class StaffEntity implements SuperEntity {
    @Id
    private String staff_id;
    private String first_name;
    private String last_name;
    private String designation;
    private String email;
    private String role;
    private String gender;
    private String joined_date;
    private String dob;
    private String address_01;
    private String address_02;
    private String address_03;
    private String address_04;
    private String address_05;
    private String phone_no;
    @OneToMany(mappedBy = "staff")
    private List<EquipmentEntity> equipment;
    @OneToMany(mappedBy = "staff")
    private List<VehicleEntity> vehicle;
    @OneToMany(mappedBy = "staff")
    private List<FieldStaffDetailsEntity> fieldStaffDetails;
    @OneToMany(mappedBy = "staff")
    private List<StaffMonitoringDetails> staffMonitoringDetails;
}
