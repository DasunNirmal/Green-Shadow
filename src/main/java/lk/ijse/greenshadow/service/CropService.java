package lk.ijse.greenshadow.service;

public interface CropService {
    void saveCrops(String cropCode, String commonName, String scientificName, String category, String base67FieldImg, String season, String field_code);
}
