package lk.ijse.greenshadow.service.impl;

import lk.ijse.greenshadow.dao.EquipmentDao;
import lk.ijse.greenshadow.dao.FieldDao;
import lk.ijse.greenshadow.dao.StaffDao;
import lk.ijse.greenshadow.dto.impl.EquipmentDtoImpl;
import lk.ijse.greenshadow.entity.impl.EquipmentEntity;
import lk.ijse.greenshadow.entity.impl.FieldEntity;
import lk.ijse.greenshadow.entity.impl.StaffEntity;
import lk.ijse.greenshadow.exception.DataPersistException;
import lk.ijse.greenshadow.service.EquipmentService;
import lk.ijse.greenshadow.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    private EquipmentDao equipmentDao;
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private FieldDao fieldDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveEquipment(EquipmentDtoImpl equipmentDto) {
        /*Get the existing entity of there is one or create the EquipmentEntity*/
        EquipmentEntity equipmentEntity = equipmentDao.findById(equipmentDto.getEq_code())
                .orElseGet(() -> mapping.toEquipmentEntity(equipmentDto));

        if (equipmentDto.getStaff_id() != null) {
            Optional<StaffEntity> staff = staffDao.findById(equipmentDto.getStaff_id());
            if (staff.isPresent()) {
                equipmentEntity.setStaff(staff.get());
            } else {
                equipmentEntity.setStaff(null); /*set to null if not found*/
            }
        } else {
            equipmentEntity.setStaff(null);
        }

        if (equipmentDto.getField_code() != null) {
            Optional<FieldEntity> field = fieldDao.findById(equipmentDto.getField_code());
            if (field.isPresent()) {
                equipmentEntity.setField(field.get());
            } else {
                equipmentEntity.setField(null); /*set to null if not found*/
            }
        } else {
            equipmentEntity.setField(null);
        }

        equipmentDao.save(equipmentEntity);
    }

    @Override
    public List<EquipmentDtoImpl> loadAllEquipments() {
        return mapping.toAllEquipments(equipmentDao.findAll());
    }

    @Override
    public void deleteEquipment(String equipmentCode) {
        Optional<EquipmentEntity> equipmentFound = equipmentDao.findById(equipmentCode);
        if (equipmentFound.isEmpty()) {
            throw new DataPersistException("Equipment Not Found");
        } else {
            equipmentDao.deleteById(equipmentCode);
        }
    }
}
