package lk.ijse.greenshadow.dto.impl;

import lk.ijse.greenshadow.dto.SuperDto;
import lk.ijse.greenshadow.dto.VehicleStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehicleDtoImpl implements SuperDto, VehicleStatus {
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
    private String staff_id;
}