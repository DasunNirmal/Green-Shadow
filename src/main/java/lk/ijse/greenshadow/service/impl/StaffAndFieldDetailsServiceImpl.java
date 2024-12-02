package lk.ijse.greenshadow.service.impl;

import lk.ijse.greenshadow.dao.FieldDao;
import lk.ijse.greenshadow.dao.StaffDao;
import lk.ijse.greenshadow.dao.StaffFieldDetailsDao;
import lk.ijse.greenshadow.dto.impl.StaffFiledDtoImpl;
import lk.ijse.greenshadow.entity.impl.FieldEntity;
import lk.ijse.greenshadow.entity.impl.FieldStaffDetailsEntity;
import lk.ijse.greenshadow.entity.impl.StaffEntity;
import lk.ijse.greenshadow.exception.DataPersistException;
import lk.ijse.greenshadow.exception.DetailsNotFoundException;
import lk.ijse.greenshadow.exception.FieldNotFoundException;
import lk.ijse.greenshadow.exception.StaffNotFoundException;
import lk.ijse.greenshadow.service.StaffAndFieldDetailsService;
import lk.ijse.greenshadow.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StaffAndFieldDetailsServiceImpl implements StaffAndFieldDetailsService {
    @Autowired
    private StaffFieldDetailsDao staffFieldDetailsDao;
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private FieldDao fieldDao;
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

    @Override
    public void deleteDetails(String detailsId) {
        Optional<FieldStaffDetailsEntity> detailsFound = staffFieldDetailsDao.findById(detailsId);
        if (detailsFound.isEmpty()) {
            throw new DetailsNotFoundException("Details not found");
        }
        staffFieldDetailsDao.deleteById(detailsId);
    }

    @Override
    public void updateDetails(String detailsID, StaffFiledDtoImpl detailsDto) {
        Optional<FieldStaffDetailsEntity> details = staffFieldDetailsDao.findById(detailsID);
        if (details.isPresent()) {
            details.get().setFirst_name(detailsDto.getFirst_name());
            details.get().setEmail(detailsDto.getEmail());
            details.get().setPhone_no(detailsDto.getPhone_no());
            details.get().setRole(detailsDto.getRole());

            Optional<StaffEntity> staff = staffDao.findById(detailsID);
            if (staff.isPresent()) {
                details.get().setStaff(staff.get());
            } else {
                throw new StaffNotFoundException("Staff not found");
            }
            Optional<FieldEntity> field = fieldDao.findById(detailsDto.getField_code());
            if (field.isPresent()) {
                details.get().setField(field.get());
            } else {
                throw new FieldNotFoundException("Field not found");
            }
        }
    }
}
