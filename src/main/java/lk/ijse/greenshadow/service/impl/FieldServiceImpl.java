package lk.ijse.greenshadow.service.impl;

import lk.ijse.greenshadow.dao.FieldDao;
import lk.ijse.greenshadow.dto.impl.FieldDtoImpl;
import lk.ijse.greenshadow.entity.impl.FieldEntity;
import lk.ijse.greenshadow.exception.DataPersistException;
import lk.ijse.greenshadow.exception.FieldNotFoundException;
import lk.ijse.greenshadow.service.FieldService;
import lk.ijse.greenshadow.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FieldServiceImpl implements FieldService {
    @Autowired
    private FieldDao fieldDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveFields(String fieldID, String fieldName, String fieldLocation, String fieldSize, String base67FieldImg01, String base67FieldImg02) {
        FieldDtoImpl fieldDto = new FieldDtoImpl();
        fieldDto.setField_code(fieldID);
        fieldDto.setField_name(fieldName);
        fieldDto.setField_location(fieldLocation);
        fieldDto.setExtent_size(Double.parseDouble(fieldSize));
        fieldDto.setImg_01(base67FieldImg01);
        fieldDto.setImg_02(base67FieldImg02);

        FieldEntity saved = fieldDao.save(mapping.toFieldEntity(fieldDto));
        if (saved == null) {
            throw new DataPersistException("Failed to save field");
        }
    }

    @Override
    public List<FieldDtoImpl> loadAllFields() {
        return mapping.toAllFields(fieldDao.findAll());
    }

    @Override
    public FieldDtoImpl getFieldsByID(String fieldCode) {
        if (fieldDao.existsById(fieldCode)) {
            FieldEntity field = fieldDao.getReferenceById(fieldCode);
            return mapping.toFieldDto(field);
        }else {
            throw new FieldNotFoundException("Field not found");
        }
    }

    @Override
    public void deleteField(String fieldCode) {
        Optional<FieldEntity> fieldFound = fieldDao.findById(fieldCode);
        if (fieldFound.isEmpty()) {
            throw new FieldNotFoundException("Field not found");
        } else {
            fieldDao.deleteById(fieldCode);
        }
    }
}
