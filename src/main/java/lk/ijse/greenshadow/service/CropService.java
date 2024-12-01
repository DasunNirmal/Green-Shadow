package lk.ijse.greenshadow.service;

import lk.ijse.greenshadow.dto.impl.FieldDtoImpl;

import java.util.List;

public interface CropService {
    void saveFields(String fieldID, String fieldName, String fieldLocation, String fieldSize, String base67FieldImg01, String base67FieldImg02);
    List<FieldDtoImpl> loadAllFields();
    FieldDtoImpl getFieldsByID(String fieldCode);
    void deleteField(String fieldCode);
    void updateFields(String fieldID, String fieldName, String fieldLocation, String fieldSize, String base67FieldImg01, String base67FieldImg02);
}
