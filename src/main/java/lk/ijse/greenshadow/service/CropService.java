package lk.ijse.greenshadow.service;

import lk.ijse.greenshadow.dto.impl.CropDtoImpl;

import java.util.List;

public interface CropService {
    void saveCrops(String cropCode, String commonName, String scientificName, String category, String base67FieldImg, String season, String field_code);
    List<CropDtoImpl> loadAllCrops();
    void deleteCrop(String cropCode);
    void updateCrops(String cropCode, String commonName, String scientificName, String category, String base67FieldImg, String season, String fieldCode);
}
