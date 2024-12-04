package lk.ijse.greenshadow.dto.impl;

import lk.ijse.greenshadow.dto.StaffLogStatus;
import lk.ijse.greenshadow.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StaffLogDtoImpl implements SuperDto, StaffLogStatus {
    private String detail_id;
    private String staff_id;
    private String log_code;
    private String first_name;
    private String phone_no;
    private String details;
}
