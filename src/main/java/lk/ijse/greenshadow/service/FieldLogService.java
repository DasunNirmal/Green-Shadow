package lk.ijse.greenshadow.service;

import lk.ijse.greenshadow.dto.impl.FieldLogDtoImpl;

import java.util.List;

public interface FieldLogService {
    void saveDetails(String logCode, String base67Img, String details, String logDate, String fieldCode, String fieldName, String fieldLocation);
    List<FieldLogDtoImpl> loadAllDetails();
    void deleteDetails(String detailsID);
    void updateDetails(String logCode, String base67Img, String details, String logDate, String fieldCode, String fieldName, String fieldLocation);
    FieldLogDtoImpl getFieldLogByID(String logCode);
}
