package lk.ijse.greenshadow.controller;

import lk.ijse.greenshadow.dto.impl.StaffFiledDtoImpl;
import lk.ijse.greenshadow.exception.DataPersistException;
import lk.ijse.greenshadow.exception.StaffNotFoundException;
import lk.ijse.greenshadow.service.StaffAndFieldDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("api/v1/staffAndFieldsDetails")
public class StaffAndFieldDetailsController {
    @Autowired
    private StaffAndFieldDetailsService staffAndFieldDetailsService;

    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveStaffs(@RequestBody StaffFiledDtoImpl staffFiledDto) {
        try {
            System.out.println(staffFiledDto);
            staffAndFieldDetailsService.saveDetails(staffFiledDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StaffFiledDtoImpl> getAllStaff() {
        return staffAndFieldDetailsService.loadAllStaffDetails();
    }

    @GetMapping(value = "/{staff_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StaffFiledDtoImpl searchStaffByID(@PathVariable("staff_id") String staffID) {
        return staffAndFieldDetailsService.getDetailsByID(staffID);
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    @DeleteMapping(value = "/{staff_id}")
    public ResponseEntity<Void> deleteDetails(@PathVariable("staff_id") String details_id) {
        try {
            staffAndFieldDetailsService.deleteDetails(details_id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (StaffNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATIVE')")
    @PatchMapping(value = "/{staff_id}")
    public ResponseEntity<Void> updateDetails(@PathVariable("staff_id") String detailsID, @RequestBody StaffFiledDtoImpl detailsDto) {
        try {
            staffAndFieldDetailsService.updateDetails(detailsID, detailsDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (StaffNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
