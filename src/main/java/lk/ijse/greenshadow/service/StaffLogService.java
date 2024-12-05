package lk.ijse.greenshadow.service;

import lk.ijse.greenshadow.dto.impl.FieldLogDtoImpl;
import lk.ijse.greenshadow.dto.impl.StaffLogDtoImpl;

import java.util.List;

public interface StaffLogService {
    void saveDetails(String logCode, String base67Img, String details, String logDate, String staffId, String firstName, String phoneNo);
    List<StaffLogDtoImpl> loadAllDetails();
    void deleteDetails(String detailsID);
}
