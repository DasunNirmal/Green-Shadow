package lk.ijse.greenshadow.service;

import lk.ijse.greenshadow.dto.impl.CropLogDtoImpl;

import java.util.List;

public interface CropLogService {
    void saveDetails(String logCode, String base67Img, String details, String logDate, String cropCode, String cropName);
    List<CropLogDtoImpl> loadAllDetails();
}
