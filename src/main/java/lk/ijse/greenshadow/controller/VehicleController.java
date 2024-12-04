package lk.ijse.greenshadow.controller;

import lk.ijse.greenshadow.dto.impl.VehicleDtoImpl;
import lk.ijse.greenshadow.exception.CropNotFoundException;
import lk.ijse.greenshadow.exception.DataPersistException;
import lk.ijse.greenshadow.exception.StaffNotFoundException;
import lk.ijse.greenshadow.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("api/v1/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveVehicles(@RequestBody VehicleDtoImpl vehicleDto) {
        try {
            vehicleService.saveVehicle(vehicleDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VehicleDtoImpl> getAllVehicles() {
        return vehicleService.loadAllVehicles();
    }

    @GetMapping(value = "/{vehicle_code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public VehicleDtoImpl searchVehicleByID(@PathVariable("vehicle_code") String vehicleCode) {
        System.out.println(vehicleCode);
        return vehicleService.getVehicleByID(vehicleCode);
    }
    
    @PatchMapping(value = "/{vehicle_code}")
    public ResponseEntity<Void> updateVehicles(@PathVariable("vehicle_code") String vehicleCode, @RequestBody VehicleDtoImpl vehicleDto) {
        try {
            vehicleService.updateVehicles(vehicleCode, vehicleDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (StaffNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{vehicle_code}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable("vehicle_code") String vehicleCode) {
        try {
            vehicleService.deleteVehicle(vehicleCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CropNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
