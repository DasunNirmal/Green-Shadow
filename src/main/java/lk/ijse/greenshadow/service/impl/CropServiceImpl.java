package lk.ijse.greenshadow.service.impl;

import lk.ijse.greenshadow.dao.CropDao;
import lk.ijse.greenshadow.dto.impl.CropDtoImpl;
import lk.ijse.greenshadow.entity.impl.CropEntity;
import lk.ijse.greenshadow.exception.DataPersistException;
import lk.ijse.greenshadow.service.CropService;
import lk.ijse.greenshadow.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CropServiceImpl implements CropService {
    @Autowired
    private CropDao cropDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveCrops(String cropCode, String commonName, String scientificName, String category, String base67FieldImg, String season, String field_code) {
        CropDtoImpl dto = getCropDto(cropCode,commonName,scientificName,category,base67FieldImg,season,field_code);

        CropEntity save = cropDao.save(mapping.toCropEntity(dto));
        if (save == null) {
            throw new DataPersistException("Failed to save crop");
        }
    }

    @Override
    public List<CropDtoImpl> loadAllCrops() {
        return mapping.toAllCrops(cropDao.findAll());
    }

    private CropDtoImpl getCropDto(String cropCode, String commonName, String scientificName, String category, String base67FieldImg, String season, String field_code) {
        CropDtoImpl cropDto = new CropDtoImpl();
        cropDto.setCrop_code(cropCode);
        cropDto.setCommon_name(commonName);
        cropDto.setScientific_name(scientificName);
        cropDto.setCategory(category);
        cropDto.setImg(base67FieldImg);
        cropDto.setSeason(season);
        cropDto.setField_code(field_code);
        return cropDto;
    }
}
