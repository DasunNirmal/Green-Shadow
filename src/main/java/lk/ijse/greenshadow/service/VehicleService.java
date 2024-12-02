package lk.ijse.greenshadow.service;

import lk.ijse.greenshadow.dto.impl.VehicleDtoImpl;

import java.util.List;

public interface VehicleService {
    void saveVehicle(VehicleDtoImpl vehicleDto);
    List<VehicleDtoImpl> loadAllVehicles();
    void deleteVehicle(String vehicleCode);
    void updateVehicles(String vehicleCode, VehicleDtoImpl vehicleDto);
}
