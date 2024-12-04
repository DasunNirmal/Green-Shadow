package lk.ijse.greenshadow.dto.impl;

import lk.ijse.greenshadow.dto.CropLogStatus;
import lk.ijse.greenshadow.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CropLogDtoImpl implements SuperDto, CropLogStatus {
    private String details_id;
    private String crop_code;
    private String log_code;
    private String details;
    private String crop_name;
}
