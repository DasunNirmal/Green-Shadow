package lk.ijse.greenshadow.service.impl;

import lk.ijse.greenshadow.dao.LogDao;
import lk.ijse.greenshadow.dao.StaffDao;
import lk.ijse.greenshadow.dao.StaffLogDao;
import lk.ijse.greenshadow.dto.impl.StaffLogDtoImpl;
import lk.ijse.greenshadow.entity.impl.MonitoringLogEntity;
import lk.ijse.greenshadow.entity.impl.StaffMonitoringDetails;
import lk.ijse.greenshadow.exception.DataPersistException;
import lk.ijse.greenshadow.service.StaffLogService;
import lk.ijse.greenshadow.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
