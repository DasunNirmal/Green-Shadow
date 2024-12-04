package lk.ijse.greenshadow.service.impl;

import lk.ijse.greenshadow.dao.FieldLogDao;
import lk.ijse.greenshadow.dao.LogDao;
import lk.ijse.greenshadow.dto.impl.FieldLogDtoImpl;
import lk.ijse.greenshadow.entity.impl.FieldMonitoringDetails;
import lk.ijse.greenshadow.entity.impl.MonitoringLogEntity;
import lk.ijse.greenshadow.exception.DataPersistException;
import lk.ijse.greenshadow.service.FieldLogService;
import lk.ijse.greenshadow.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FieldLogServiceImpl implements FieldLogService {
    @Autowired
    private FieldLogDao fieldLogDao;
    @Autowired
    private LogDao logDao;
    @Autowired
    private Mapping mapping;


    @Override
    public void saveDetails(String logCode, String base67Img, String details, String logDate, String fieldCode, String fieldName, String fieldLocation) {
        Optional<MonitoringLogEntity> code = logDao.findById(logCode);
        if (code.isPresent()) {
            FieldLogDtoImpl fieldLogDto = getFieldLogDto(logCode, base67Img, details, logDate, fieldCode, fieldName, fieldLocation);
            FieldMonitoringDetails fieldLogEntity = mapping.toFieldLogEntity(fieldLogDto);
            fieldLogEntity.setMonitoringLog(code.get());
            FieldMonitoringDetails save = fieldLogDao.save(fieldLogEntity);
            if (save == null) {
                throw new DataPersistException("Failed to save log");
            }
        } else {
            throw new DataPersistException("Log with code " + logCode + " not found");
        }
    }

    @Override
    public List<FieldLogDtoImpl> loadAllDetails() {
        return mapping.toAllFieldLogs(fieldLogDao.findAll());
    }

    private FieldLogDtoImpl getFieldLogDto(String logCode, String base67Img, String details, String logDate, String fieldCode, String fieldName, String fieldLocation) {
        FieldLogDtoImpl fieldLogDto = new FieldLogDtoImpl();
        fieldLogDto.setDetails_id(logCode);
        fieldLogDto.setField_code(fieldCode);
        fieldLogDto.setLog_code(logCode);
        fieldLogDto.setField_name(fieldName);
        fieldLogDto.setField_location(fieldLocation);
        return fieldLogDto;
    }
}
