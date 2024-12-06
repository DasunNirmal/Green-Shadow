package lk.ijse.greenshadow.controller;

import lk.ijse.greenshadow.dto.impl.CropDtoImpl;
import lk.ijse.greenshadow.dto.impl.FieldDtoImpl;
import lk.ijse.greenshadow.exception.CropNotFoundException;
import lk.ijse.greenshadow.exception.FieldNotFoundException;
import lk.ijse.greenshadow.service.CropService;
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
@RequestMapping("api/v1/crop")
public class CropController {
    @Autowired
    private CropService cropService;

    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCrops(@RequestPart("crop_code") String crop_code, @RequestPart("common_name") String common_name, @RequestPart("scientific_name") String scientific_name, @RequestPart("category") String category,
                                           @RequestPart("img") MultipartFile img,
                                           @RequestPart("season") String season,@RequestPart("field_code") String field_code) {
        String base67FieldImg = "";

        try {
            byte[] image01Bytes = img.getBytes();
            base67FieldImg = AppUtil.imageToBase64(image01Bytes);

            cropService.saveCrops(crop_code,common_name,scientific_name,category,base67FieldImg,season,field_code);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CropDtoImpl> getAllCrops() {
        return cropService.loadAllCrops();
    }

    @GetMapping(value = "/{crop_code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CropDtoImpl searchCropsByID(@PathVariable("crop_code") String cropCode) {
        return cropService.getCropByID(cropCode);
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    @PatchMapping(value = "/{crop_code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateCrops(@RequestPart("common_name") String common_name, @RequestPart("scientific_name") String scientific_name, @RequestPart("category") String category,
                                          @RequestPart("img") MultipartFile img,
                                          @RequestPart("season") String season,@RequestPart("field_code") String field_code,@PathVariable("crop_code") String crop_code) {
        String base67FieldImg = "";

        try {
            byte[] image01Bytes = img.getBytes();
            base67FieldImg = AppUtil.imageToBase64(image01Bytes);

            cropService.updateCrops(crop_code,common_name,scientific_name,category,base67FieldImg,season,field_code);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    @DeleteMapping(value = "/{crop_code}")
    public ResponseEntity<Void> deleteCrops(@PathVariable("crop_code") String cropCode) {
        try {
            cropService.deleteCrop(cropCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CropNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
