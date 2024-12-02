package lk.ijse.greenshadow.utill;

import lk.ijse.greenshadow.dto.impl.CropDtoImpl;
import lk.ijse.greenshadow.dto.impl.FieldDtoImpl;
import lk.ijse.greenshadow.dto.impl.StaffDtoImpl;
import lk.ijse.greenshadow.dto.impl.StaffFiledDtoImpl;
import lk.ijse.greenshadow.entity.impl.CropEntity;
import lk.ijse.greenshadow.entity.impl.FieldEntity;
import lk.ijse.greenshadow.entity.impl.FieldStaffDetailsEntity;
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

    /*for crop mapping*/
    public CropEntity toCropEntity(CropDtoImpl cropDto) {
        return modelMapper.map(cropDto, CropEntity.class);
    }
    public CropDtoImpl toCropDto(CropEntity cropEntity) {
        return modelMapper.map(cropEntity, CropDtoImpl.class);
    }
    public List<CropDtoImpl> toAllCrops(List<CropEntity> cropEntityList) {
        return modelMapper.map(cropEntityList, new TypeToken<List<CropDtoImpl>>() {}.getType());
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

    /*for staff and fields details mapping*/
    public FieldStaffDetailsEntity toStaffFiledDetailsEntity(StaffFiledDtoImpl staffFiledDto) {
        return modelMapper.map(staffFiledDto, FieldStaffDetailsEntity.class);
    }
    public StaffFiledDtoImpl toStaffFiledDetailsDto(FieldStaffDetailsEntity detailsEntity) {
        return modelMapper.map(detailsEntity, StaffFiledDtoImpl.class);
    }
    public List<StaffFiledDtoImpl> toAllStaffFiledDetails(List<FieldStaffDetailsEntity> detailsEntities) {
        return modelMapper.map(detailsEntities, new TypeToken<List<StaffFiledDtoImpl>>() {}.getType());
    }
}
