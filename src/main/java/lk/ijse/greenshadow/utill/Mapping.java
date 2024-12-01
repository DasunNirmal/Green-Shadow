package lk.ijse.greenshadow.utill;

import lk.ijse.greenshadow.dto.impl.FieldDtoImpl;
import lk.ijse.greenshadow.dto.impl.StaffDtoImpl;
import lk.ijse.greenshadow.entity.impl.FieldEntity;
import lk.ijse.greenshadow.entity.impl.StaffEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    /*for field mapping*/
    public FieldEntity toFieldEntity(FieldDtoImpl fieldDto) {
        return modelMapper.map(fieldDto, FieldEntity.class);
    }
    public FieldDtoImpl toFieldDto(FieldEntity fieldEntity) {
        return modelMapper.map(fieldEntity, FieldDtoImpl.class);
    }
    public List<FieldDtoImpl> toAllFields(List<FieldEntity> fieldEntityList) {
        return modelMapper.map(fieldEntityList, new TypeToken<List<FieldDtoImpl>>() {}.getType());
    }

    /*for staff mapping*/
    public StaffEntity toStaffEntity(StaffDtoImpl staffDto) {
        return modelMapper.map(staffDto, StaffEntity.class);
    }
    public StaffDtoImpl toStaffDto(StaffEntity staffEntity) {
        return modelMapper.map(staffEntity, StaffDtoImpl.class);
    }
    public List<StaffDtoImpl> toAllStaffs(List<StaffEntity> staffEntityList) {
        return modelMapper.map(staffEntityList, new TypeToken<List<StaffDtoImpl>>() {}.getType());
    }
}
