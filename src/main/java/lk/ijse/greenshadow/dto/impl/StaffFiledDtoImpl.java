package lk.ijse.greenshadow.dto.impl;

import lk.ijse.greenshadow.dto.StaffFieldStatus;
import lk.ijse.greenshadow.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StaffFiledDtoImpl implements SuperDto, StaffFieldStatus {
    private String details_id;
    private String first_name;
    private String email;
    private String phone_no;
    private String role;
    private String staff_id;
    private String field_code;
}
