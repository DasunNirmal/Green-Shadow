package lk.ijse.greenshadow.dto.impl;

import lk.ijse.greenshadow.dto.FieldLogStatus;
import lk.ijse.greenshadow.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FieldLogDtoImpl implements SuperDto, FieldLogStatus {
    private String details_id;
    private String field_code;
    private String log_code;
    private String field_name;
    private String field_location;
}
