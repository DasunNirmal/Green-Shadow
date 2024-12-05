package lk.ijse.greenshadow.service.impl;

import lk.ijse.greenshadow.dao.LogDao;
import lk.ijse.greenshadow.dto.impl.LogDtoImpl;
import lk.ijse.greenshadow.entity.impl.MonitoringLogEntity;
import lk.ijse.greenshadow.exception.DataPersistException;
import lk.ijse.greenshadow.exception.LogNotFoundException;
import lk.ijse.greenshadow.service.LogService;
import lk.ijse.greenshadow.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDao logDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveLogs(String logCode, String base67Img, String details, String logDate, String fieldCode, String fieldName, String fieldLocation) {
        LogDtoImpl logDto = getLogDto(logCode,base67Img,details,logDate,fieldCode,fieldName,fieldLocation);
        MonitoringLogEntity save = logDao.save(mapping.toLogEntity(logDto));
        if (save == null) {
            throw new DataPersistException("Failed to save log");
        }
    }

    @Override
    public List<LogDtoImpl> loadAllLogs() {
        return mapping.toAllLogs(logDao.findAll());
    }

    @Override
    public void deleteLogs(String logCode) {
        Optional<MonitoringLogEntity> logFound = logDao.findById(logCode);
        if (logFound.isEmpty()) {
            throw new LogNotFoundException("Log not found");
        } else {
            logDao.deleteById(logCode);
        }
    }

    @Override
    public void updateLogs(String logCode, String base67Img, String details, String logDate, String fieldCode, String fieldName, String fieldLocation) {
        LogDtoImpl logDto = getLogDto(logCode,base67Img,details,logDate,fieldCode,fieldName,fieldLocation);
        Optional<MonitoringLogEntity> logFound = logDao.findById(logCode);
        if (logFound.isPresent()) {
            logFound.get().setImg(logDto.getImg());
            logFound.get().setDetails(logDto.getDetails());
            logFound.get().setLog_date(logDto.getLog_date());
            logFound.get().setLog_code(logDto.getLog_code());
        }
    }

    private LogDtoImpl getLogDto(String logCode, String base67Img, String details, String logDate, String fieldCode, String fieldName, String fieldLocation) {
        LogDtoImpl logDto = new LogDtoImpl();
        logDto.setLog_code(logCode);
        logDto.setImg(base67Img);
        logDto.setDetails(details);
        logDto.setLog_date(logDate);
        return logDto;
    }
}
