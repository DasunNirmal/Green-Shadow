package lk.ijse.greenshadow.service;

import lk.ijse.greenshadow.dto.impl.LogDtoImpl;

import java.util.List;

public interface LogService {
    void saveLogs(String logCode, String base67Img, String details, String logDate, String fieldCode, String fieldName, String fieldLocation);
    List<LogDtoImpl> loadAllLogs();
    void deleteLogs(String logCode);
    void updateLogs(String logCode, String base67Img, String details, String logDate, String fieldCode, String fieldName, String fieldLocation);
}
