package lk.ijse.greenshadow.dto.impl;

import lk.ijse.greenshadow.dto.CropStatus;
import lk.ijse.greenshadow.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CropDtoImpl implements SuperDto, CropStatus {
    private String crop_code;
    private String common_name;
    private String scientific_name;
    private String category;
    private String img;
    private String season;
    private String field_code;
}
