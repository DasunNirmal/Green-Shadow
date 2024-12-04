package lk.ijse.greenshadow.service.impl;

import lk.ijse.greenshadow.dao.LogDao;
import lk.ijse.greenshadow.dto.impl.LogDtoImpl;
import lk.ijse.greenshadow.entity.impl.MonitoringLogEntity;
import lk.ijse.greenshadow.exception.DataPersistException;
import lk.ijse.greenshadow.service.LogService;
import lk.ijse.greenshadow.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    private LogDtoImpl getLogDto(String logCode, String base67Img, String details, String logDate, String fieldCode, String fieldName, String fieldLocation) {
        LogDtoImpl logDto = new LogDtoImpl();
        logDto.setLog_code(logCode);
        logDto.setImg(base67Img);
        logDto.setDetails(details);
        logDto.setLog_date(logDate);
        return logDto;
    }
}
