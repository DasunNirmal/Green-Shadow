package lk.ijse.greenshadow.controller;

import lk.ijse.greenshadow.service.FieldLogService;
import lk.ijse.greenshadow.utill.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("api/v1/fieldLogs")
public class FieldLogsController {
    @Autowired
    private FieldLogService fieldLogService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveDetails(@RequestPart("log_code") String log_code, @RequestPart("img") MultipartFile img, @RequestPart("details") String details, @RequestPart("log_date") String log_date,
                                         @RequestPart("field_code") String field_code,
                                         @RequestPart("field_name") String field_name, @RequestPart("field_location") String field_location) {
        String base67Img = "";

        try {
            byte[] imageBytes = img.getBytes();
            base67Img = AppUtil.imageToBase64(imageBytes);
            System.out.println(log_code);
            fieldLogService.saveDetails(log_code,base67Img,details,log_date,field_code,field_name,field_location);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
