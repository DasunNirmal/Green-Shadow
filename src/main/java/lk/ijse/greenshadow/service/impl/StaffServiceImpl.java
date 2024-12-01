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

    @Override
    public StaffDtoImpl getStaffByID(String staffID) {
        if (staffDao.existsById(staffID)) {
            StaffEntity staff = staffDao.getReferenceById(staffID);
            return mapping.toStaffDto(staff);
        } else {
            throw new StaffNotFoundException("Staff Not Found");
        }
    }

    @Override
    public void updateStaff(String staffID, StaffDtoImpl staffDto) {
        Optional<StaffEntity> staff = staffDao.findById(staffID);
        if (staff.isPresent()) {
            staff.get().setFirst_name(staffDto.getFirst_name());
            staff.get().setLast_name(staffDto.getLast_name());
            staff.get().setDesignation(staffDto.getDesignation());
            staff.get().setEmail(staffDto.getEmail());
            staff.get().setRole(staffDto.getRole());
            staff.get().setGender(staffDto.getGender());
            staff.get().setJoined_date(staffDto.getJoined_date());
            staff.get().setDob(staffDto.getDob());
            staff.get().setAddress_01(staffDto.getAddress_01());
            staff.get().setAddress_02(staffDto.getAddress_02());
            staff.get().setAddress_03(staffDto.getAddress_03());
            staff.get().setAddress_04(staffDto.getAddress_04());
            staff.get().setAddress_05(staffDto.getAddress_05());
            staff.get().setPhone_no(staffDto.getPhone_no());
            staff.get().setStaff_id(staffDto.getStaff_id());
        }
    }
}
