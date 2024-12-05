package lk.ijse.greenshadow.controller;

import lk.ijse.greenshadow.dto.impl.FieldLogDtoImpl;
import lk.ijse.greenshadow.dto.impl.StaffLogDtoImpl;
import lk.ijse.greenshadow.service.StaffLogService;
import lk.ijse.greenshadow.utill.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("api/v1/staffLogs")
public class StaffLogsController {
    @Autowired
    private StaffLogService staffLogService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveDetails(@RequestPart("log_code") String log_code, @RequestPart("img") MultipartFile img, @RequestPart("details") String details, @RequestPart("log_date") String log_date,
                                            @RequestPart("code") String staff_id,
                                            @RequestPart("name") String first_name, @RequestPart("additional") String phone_no) {
        String base67Img = "";

        try {
            byte[] imageBytes = img.getBytes();
            base67Img = AppUtil.imageToBase64(imageBytes);
            staffLogService.saveDetails(log_code,base67Img,details,log_date,staff_id,first_name,phone_no);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StaffLogDtoImpl> getAllDetails() {
        return staffLogService.loadAllDetails();
    }
}
