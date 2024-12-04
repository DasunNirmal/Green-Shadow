package lk.ijse.greenshadow.dto.impl;

import lk.ijse.greenshadow.dto.EquipmentStatus;
import lk.ijse.greenshadow.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EquipmentDaoImpl implements SuperDto, EquipmentStatus {
    private String eq_code;
    private String name;
    private String type;
    private String status;
    private String staff_id;
    private String field_code;
    private String field_name;
    private String field_location;
    private String first_name;
    private String role;
    private String phone_no;
}
