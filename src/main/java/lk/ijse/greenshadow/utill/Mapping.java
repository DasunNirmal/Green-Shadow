package lk.ijse.greenshadow.utill;

import lk.ijse.greenshadow.dto.impl.FieldDtoImpl;
import lk.ijse.greenshadow.entity.impl.FieldEntity;
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
}
