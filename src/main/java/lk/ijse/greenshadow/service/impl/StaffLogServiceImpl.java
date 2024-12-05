package lk.ijse.greenshadow.service.impl;

import lk.ijse.greenshadow.dao.LogDao;
import lk.ijse.greenshadow.dao.StaffDao;
import lk.ijse.greenshadow.dao.StaffLogDao;
import lk.ijse.greenshadow.dto.impl.FieldLogDtoImpl;
import lk.ijse.greenshadow.dto.impl.StaffLogDtoImpl;
import lk.ijse.greenshadow.entity.impl.MonitoringLogEntity;
import lk.ijse.greenshadow.entity.impl.StaffEntity;
import lk.ijse.greenshadow.entity.impl.StaffMonitoringDetails;
import lk.ijse.greenshadow.exception.DataPersistException;
import lk.ijse.greenshadow.exception.LogNotFoundException;
import lk.ijse.greenshadow.exception.StaffLogNotFoundException;
import lk.ijse.greenshadow.exception.StaffNotFoundException;
import lk.ijse.greenshadow.service.StaffLogService;
import lk.ijse.greenshadow.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StaffLogServiceImpl implements StaffLogService {
    @Autowired
    private StaffLogDao staffLogDao;
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private LogDao logDao;
    @Autowired
    private Mapping mapping;


    @Override
    public void saveDetails(String logCode, String base67Img, String details, String logDate, String staffId, String firstName, String phoneNo) {
        Optional<MonitoringLogEntity> code = logDao.findById(logCode);
        if (code.isPresent()) {
            StaffLogDtoImpl staffLogDto = getDetails(logCode, base67Img, details, logDate, staffId, firstName, phoneNo);
            StaffMonitoringDetails staffLogEntity = mapping.toStaffLogEntity(staffLogDto);
            staffLogEntity.setMonitoringLog(code.get());
            StaffMonitoringDetails save = staffLogDao.save(staffLogEntity);
            if (save == null) {
                throw new DataPersistException("Failed to save log");
            }
        } else {
            throw new DataPersistException("Log with code " + logCode + " not found");
        }
    }

    @Override
    public List<StaffLogDtoImpl> loadAllDetails() {
        return mapping.toAllStaffLogs(staffLogDao.findAll());
    }

    @Override
    public void deleteDetails(String detailsID) {
        Optional<StaffMonitoringDetails> detailsFound = staffLogDao.findById(detailsID);
        if (detailsFound.isEmpty()) {
            throw new StaffLogNotFoundException("Details not found");
        } else {
            staffLogDao.deleteById(detailsID);
        }
    }

    @Override
    public void updateDetails(String logCode, String base67Img, String details, String logDate, String staffId, String firstName, String phoneNo) {
        StaffLogDtoImpl dto = getDetails(logCode, base67Img, details, logDate, staffId, firstName, phoneNo);
        Optional<StaffMonitoringDetails> staffLog = staffLogDao.findById(logCode);
        if (staffLog.isPresent()) {
            Optional<StaffEntity> staff = staffDao.findById(staffId);
            if (staff.isPresent()) {
                staffLog.get().setStaff(staff.get());
            } else {
                throw new StaffNotFoundException("Staff not found");
            }
            Optional<MonitoringLogEntity> code = logDao.findById(logCode);
            if (code.isPresent()) {
                staffLog.get().setMonitoringLog(code.get());
            } else {
                throw new LogNotFoundException("Log not found");
            }
            staffLog.get().setFirst_name(dto.getFirst_name());
            staffLog.get().setPhone_no(dto.getPhone_no());
            staffLog.get().setDetails(dto.getDetails());
            staffLog.get().setDetail_id(logCode);
        }
    }

    @Override
    public StaffLogDtoImpl getStaffLogByID(String logCode) {
        if (staffLogDao.existsById(logCode)) {
            StaffMonitoringDetails log = staffLogDao.getReferenceById(logCode);
            return mapping.toStaffLogDto(log);
        } else {
            throw new StaffLogNotFoundException("Log Details not found");
        }
    }

    private StaffLogDtoImpl getDetails(String logCode, String base67Img, String details, String logDate, String staffId, String firstName, String phoneNo) {
        StaffLogDtoImpl dto = new StaffLogDtoImpl();
        dto.setDetail_id(logCode);
        dto.setStaff_id(staffId);
        dto.setLog_code(logCode);
        dto.setFirst_name(firstName);
        dto.setPhone_no(phoneNo);
        dto.setDetails(details);
        return dto;
    }
}
