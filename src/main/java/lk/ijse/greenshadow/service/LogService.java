package lk.ijse.greenshadow.service;

public interface LogService {
    void saveLogs(String logCode, String base67Img, String details, String logDate, String fieldCode, String fieldName, String fieldLocation);
}
