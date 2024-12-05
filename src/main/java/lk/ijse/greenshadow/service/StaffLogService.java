package lk.ijse.greenshadow.service;

public interface StaffLogService {
    void saveDetails(String logCode, String base67Img, String details, String logDate, String staffId, String firstName, String phoneNo);
}
