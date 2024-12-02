package lk.ijse.greenshadow.service.impl;

import lk.ijse.greenshadow.dao.StaffFieldDetailsDao;
import lk.ijse.greenshadow.dto.impl.StaffFiledDtoImpl;
import lk.ijse.greenshadow.entity.impl.FieldStaffDetailsEntity;
import lk.ijse.greenshadow.exception.DataPersistException;
import lk.ijse.greenshadow.service.StaffAndFieldDetailsService;
import lk.ijse.greenshadow.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StaffAndFieldDetailsServiceImpl implements StaffAndFieldDetailsService {
    @Autowired
    private StaffFieldDetailsDao staffFieldDetailsDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveDetails(StaffFiledDtoImpl staffFiledDto) {
        FieldStaffDetailsEntity save = staffFieldDetailsDao.save(mapping.toStaffFiledDetailsEntity(staffFiledDto));
        if (save == null) {
            throw new DataPersistException("Save Staff Failed");
        }
    }

    @Override
    public List<StaffFiledDtoImpl> loadAllStaffDetails() {
        return mapping.toAllStaffFiledDetails(staffFieldDetailsDao.findAll());
    }
}
