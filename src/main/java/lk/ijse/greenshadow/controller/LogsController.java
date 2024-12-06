package lk.ijse.greenshadow.controller;

import lk.ijse.greenshadow.dto.impl.LogDtoImpl;
import lk.ijse.greenshadow.exception.CropNotFoundException;
import lk.ijse.greenshadow.service.LogService;
import lk.ijse.greenshadow.utill.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("api/v1/logs")
public class LogsController {
    @Autowired
    private LogService logService;

    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST') or hasRole('ADMINISTRATIVE')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveLogs(@RequestPart("log_code") String log_code, @RequestPart("img") MultipartFile img, @RequestPart("details") String details, @RequestPart("log_date") String log_date,
                                          @RequestPart("code") String field_code,
                                          @RequestPart("name") String field_name, @RequestPart("additional") String field_location) {
        String base67Img = "";

        try {
            byte[] imageBytes = img.getBytes();
            base67Img = AppUtil.imageToBase64(imageBytes);

            logService.saveLogs(log_code,base67Img,details,log_date,field_code,field_name,field_location);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LogDtoImpl> getAllLOgs() {
        return logService.loadAllLogs();
    }

    @GetMapping(value = "/{log_code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public LogDtoImpl searchLogsByID(@PathVariable("log_code") String logCode) {
        return logService.getLogByID(logCode);
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST') or hasRole('ADMINISTRATIVE')")
    @PatchMapping(value = "/{log_code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateLogs(@RequestPart("img") MultipartFile img, @RequestPart("details") String details, @RequestPart("log_date") String log_date, @RequestPart("code") String field_code,
                                         @RequestPart("name") String field_name, @RequestPart("additional") String field_location,@PathVariable("log_code") String log_code) {
        String base67Img = "";

        try {
            byte[] imageBytes = img.getBytes();
            base67Img = AppUtil.imageToBase64(imageBytes);

            logService.updateLogs(log_code,base67Img,details,log_date,field_code,field_name,field_location);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST') or hasRole('ADMINISTRATIVE')")
    @DeleteMapping(value = "/{log_code}")
    public ResponseEntity<Void> deleteLogs(@PathVariable("log_code") String logCode) {
        try {
            logService.deleteLogs(logCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CropNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
