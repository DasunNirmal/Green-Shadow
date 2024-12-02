package lk.ijse.greenshadow.service.impl;

import lk.ijse.greenshadow.dao.StaffDao;
import lk.ijse.greenshadow.dao.VehicleDao;
import lk.ijse.greenshadow.dto.impl.VehicleDtoImpl;
import lk.ijse.greenshadow.entity.impl.StaffEntity;
import lk.ijse.greenshadow.entity.impl.VehicleEntity;
import lk.ijse.greenshadow.exception.DataPersistException;
import lk.ijse.greenshadow.exception.StaffNotFoundException;
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
    private StaffDao staffDao;
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

    @Override
    public void updateVehicles(String vehicleCode, VehicleDtoImpl vehicleDto) {
        Optional<VehicleEntity> vehicle = vehicleDao.findById(vehicleCode);
        if (vehicle.isPresent()) {
            vehicle.get().setLicense_plate(vehicleDto.getLicense_plate());
            vehicle.get().setFuel_type(vehicleDto.getFuel_type());
            vehicle.get().setRole(vehicleDto.getRole());
            vehicle.get().setRemarks(vehicleDto.getRemarks());
            vehicle.get().setVehicle_category(vehicleDto.getVehicle_category());
            vehicle.get().setStatus(vehicleDto.getStatus());
            vehicle.get().setFirst_name(vehicleDto.getFirst_name());
            vehicle.get().setPhone_no(vehicleDto.getPhone_no());
            vehicle.get().setEmail(vehicleDto.getEmail());

            if (vehicleDto.getStaff_id() != null) {
                Optional<StaffEntity> staff = staffDao.findById(vehicleDto.getStaff_id());
                vehicle.get().setStaff(staff.orElse(null)); /*set staff if provided or null*/
            } else {
                vehicle.get().setStaff(null);
            }
            vehicle.get().setVehicle_code(vehicleCode);
        }
    }
}
