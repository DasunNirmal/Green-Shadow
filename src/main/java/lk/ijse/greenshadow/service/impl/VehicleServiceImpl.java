package lk.ijse.greenshadow.service.impl;

import lk.ijse.greenshadow.dao.VehicleDao;
import lk.ijse.greenshadow.dto.impl.VehicleDtoImpl;
import lk.ijse.greenshadow.entity.impl.VehicleEntity;
import lk.ijse.greenshadow.exception.DataPersistException;
import lk.ijse.greenshadow.exception.VehicleNotFoundException;
import lk.ijse.greenshadow.service.VehicleService;
import lk.ijse.greenshadow.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleDao vehicleDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveVehicle(VehicleDtoImpl vehicleDto) {
        VehicleEntity save = vehicleDao.save(mapping.toVehicleEntity(vehicleDto));
        if (save == null) {
            throw new DataPersistException("Save Vehicle Failed");
        }
    }

    @Override
    public List<VehicleDtoImpl> loadAllVehicles() {
        return mapping.toAllVehicles(vehicleDao.findAll());
    }

    @Override
    public void deleteVehicle(String vehicleCode) {
        Optional<VehicleEntity> vehicleFound = vehicleDao.findById(vehicleCode);
        if (vehicleFound.isEmpty()) {
            throw new VehicleNotFoundException("Vehicle Not Found");
        } else {
            vehicleDao.deleteById(vehicleCode);
        }
    }
}
