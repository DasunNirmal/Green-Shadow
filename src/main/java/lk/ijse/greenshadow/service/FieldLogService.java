package lk.ijse.greenshadow.service;

public interface FieldLogService {
    void saveDetails(String logCode, String base67Img, String details, String logDate, String fieldCode, String fieldName, String fieldLocation);
}
