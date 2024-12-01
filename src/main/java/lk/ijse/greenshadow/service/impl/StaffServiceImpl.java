package lk.ijse.greenshadow.service.impl;

import lk.ijse.greenshadow.dao.StaffDao;
import lk.ijse.greenshadow.dto.impl.StaffDtoImpl;
import lk.ijse.greenshadow.entity.impl.StaffEntity;
import lk.ijse.greenshadow.exception.DataPersistException;
import lk.ijse.greenshadow.exception.StaffNotFoundException;
import lk.ijse.greenshadow.service.StaffService;
import lk.ijse.greenshadow.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveStaff(StaffDtoImpl staffDto) {
        StaffEntity save = staffDao.save(mapping.toStaffEntity(staffDto));
        if (save == null) {
            throw new DataPersistException("Save Staff Failed");
        }
    }

    @Override
    public List<StaffDtoImpl> loadAllStaff() {
        return mapping.toAllStaffs(staffDao.findAll());
    }

    @Override
    public void deleteStaff(String staffID) {
        Optional<StaffEntity> staffFound = staffDao.findById(staffID);
        if (staffFound.isEmpty()) {
            throw new StaffNotFoundException("Staff Not Found");
        }
        staffDao.deleteById(staffID);
    }
}
