package lk.ijse.greenshadow.service;

import lk.ijse.greenshadow.dto.impl.EquipmentDtoImpl;

import java.util.List;

public interface EquipmentService {
    void saveEquipment(EquipmentDtoImpl equipmentDto);
    List<EquipmentDtoImpl> loadAllEquipments();
    void deleteEquipment(String equipmentCode);
    void updateEquipments(String equipmentCode, EquipmentDtoImpl equipmentDto);
}
