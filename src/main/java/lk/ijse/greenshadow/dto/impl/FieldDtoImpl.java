package lk.ijse.greenshadow.dto.impl;

import lk.ijse.greenshadow.dto.FieldStatus;
import lk.ijse.greenshadow.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FieldDtoImpl implements SuperDto, FieldStatus {
    private String field_code;
    private String field_name;
    private String field_location;
    private double extent_size;
    private String img_01;
    private String img_02;
}
