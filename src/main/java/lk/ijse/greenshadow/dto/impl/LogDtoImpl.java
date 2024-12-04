package lk.ijse.greenshadow.dto.impl;

import lk.ijse.greenshadow.dto.LogStatus;
import lk.ijse.greenshadow.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LogDtoImpl implements SuperDto, LogStatus {
    private String log_code;
    private String img;
    private String details;
    private String log_date;
}
