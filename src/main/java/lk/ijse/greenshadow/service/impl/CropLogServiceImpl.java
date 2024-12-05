package lk.ijse.greenshadow.service.impl;

import lk.ijse.greenshadow.dao.CropDao;
import lk.ijse.greenshadow.dao.CropLogDao;
import lk.ijse.greenshadow.dao.LogDao;
import lk.ijse.greenshadow.dto.impl.CropLogDtoImpl;
import lk.ijse.greenshadow.entity.impl.CropEntity;
import lk.ijse.greenshadow.entity.impl.CropMonitoringDetails;
import lk.ijse.greenshadow.entity.impl.MonitoringLogEntity;
import lk.ijse.greenshadow.exception.DataPersistException;
import lk.ijse.greenshadow.service.CropLogService;
import lk.ijse.greenshadow.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CropLogServiceImpl implements CropLogService {
    @Autowired
    private CropLogDao cropLogDao;
    @Autowired
    private LogDao logDao;
    @Autowired
    private CropDao cropDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveDetails(String logCode, String base67Img, String details, String logDate, String cropCode, String cropName) {
        Optional<MonitoringLogEntity> code = logDao.findById(logCode);
        Optional<CropEntity> crop = cropDao.findById(cropCode);
        if (code.isPresent() && crop.isPresent()) {
            CropLogDtoImpl dto = getDetails(logCode,base67Img,details,logDate,cropCode,cropName);
            CropMonitoringDetails cropLogEntity = mapping.toCropLogEntity(dto);
            cropLogEntity.setMonitoringLog(code.get());
            cropLogEntity.setCrops(crop.get());
            CropMonitoringDetails save = cropLogDao.save(cropLogEntity);
            if (save == null) {
                throw new DataPersistException("Failed to save log");
            }
        } else {
            throw new DataPersistException("Log with code " + logCode + " not found");
        }
    }

    @Override
    public List<CropLogDtoImpl> loadAllDetails() {
        return cropLogDao.findAllCropLogs();
    }

    private CropLogDtoImpl getDetails(String logCode, String base67Img, String details, String logDate, String cropCode, String cropName) {
        CropLogDtoImpl dto = new CropLogDtoImpl();
        dto.setDetails_id(logCode);
        dto.setCrop_code(cropCode);
        dto.setLog_code(logCode);
        dto.setDetails(details);
        dto.setCrop_name(cropName);
        return dto;
    }
}
