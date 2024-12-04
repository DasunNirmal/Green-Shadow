package lk.ijse.greenshadow.utill;

import lk.ijse.greenshadow.dto.impl.*;
import lk.ijse.greenshadow.entity.impl.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    /*for field mapping*/
    public FieldEntity toFieldEntity(FieldDtoImpl fieldDto) {
        return modelMapper.map(fieldDto, FieldEntity.class);
    }
    public FieldDtoImpl toFieldDto(FieldEntity fieldEntity) {
        return modelMapper.map(fieldEntity, FieldDtoImpl.class);
    }
    public List<FieldDtoImpl> toAllFields(List<FieldEntity> fieldEntityList) {
        return modelMapper.map(fieldEntityList, new TypeToken<List<FieldDtoImpl>>() {}.getType());
    }

    /*for crop mapping*/
    public CropEntity toCropEntity(CropDtoImpl cropDto) {
        return modelMapper.map(cropDto, CropEntity.class);
    }
    public CropDtoImpl toCropDto(CropEntity cropEntity) {
        return modelMapper.map(cropEntity, CropDtoImpl.class);
    }
    public List<CropDtoImpl> toAllCrops(List<CropEntity> cropEntityList) {
        return modelMapper.map(cropEntityList, new TypeToken<List<CropDtoImpl>>() {}.getType());
    }

    /*for staff mapping*/
    public StaffEntity toStaffEntity(StaffDtoImpl staffDto) {
        return modelMapper.map(staffDto, StaffEntity.class);
    }
    public StaffDtoImpl toStaffDto(StaffEntity staffEntity) {
        return modelMapper.map(staffEntity, StaffDtoImpl.class);
    }
    public List<StaffDtoImpl> toAllStaffs(List<StaffEntity> staffEntityList) {
        return modelMapper.map(staffEntityList, new TypeToken<List<StaffDtoImpl>>() {}.getType());
    }

    /*for staff and fields details mapping*/
    public FieldStaffDetailsEntity toStaffFiledDetailsEntity(StaffFiledDtoImpl staffFiledDto) {
        return modelMapper.map(staffFiledDto, FieldStaffDetailsEntity.class);
    }
    public StaffFiledDtoImpl toStaffFiledDetailsDto(FieldStaffDetailsEntity detailsEntity) {
        return modelMapper.map(detailsEntity, StaffFiledDtoImpl.class);
    }
    public List<StaffFiledDtoImpl> toAllStaffFiledDetails(List<FieldStaffDetailsEntity> detailsEntities) {
        return modelMapper.map(detailsEntities, new TypeToken<List<StaffFiledDtoImpl>>() {}.getType());
    }

    /*for vehicle mapping*/
    public VehicleEntity toVehicleEntity(VehicleDtoImpl vehicleDto) {
        return modelMapper.map(vehicleDto, VehicleEntity.class);
    }
    public VehicleDtoImpl toVehicleDto(VehicleEntity vehicleEntity) {
        return modelMapper.map(vehicleEntity, VehicleDtoImpl.class);
    }
    public List<VehicleDtoImpl> toAllVehicles(List<VehicleEntity> vehicleEntities) {
        return modelMapper.map(vehicleEntities, new TypeToken<List<VehicleDtoImpl>>() {}.getType());
    }

    /*for equipment mapping*/
    public EquipmentEntity toEquipmentEntity(EquipmentDtoImpl equipmentDto) {
        return modelMapper.map(equipmentDto, EquipmentEntity.class);
    }
    public EquipmentDtoImpl toEquipmentDto(EquipmentEntity equipmentEntity) {
        return modelMapper.map(equipmentEntity, EquipmentDtoImpl.class);
    }
    public List<EquipmentDtoImpl> toAllEquipments(List<EquipmentEntity> equipmentEntities) {
        return modelMapper.map(equipmentEntities, new TypeToken<List<EquipmentDtoImpl>>() {}.getType());
    }

    /*for logs mapping*/
    public MonitoringLogEntity toLogEntity(LogDtoImpl logDto) {
        return modelMapper.map(logDto, MonitoringLogEntity.class);
    }
    public LogDtoImpl toLogDto(MonitoringLogEntity monitoringLogEntity) {
        return modelMapper.map(monitoringLogEntity, LogDtoImpl.class);
    }
    public List<LogDtoImpl> toAllLogs(List<MonitoringLogEntity> monitoringLogEntities) {
        return modelMapper.map(monitoringLogEntities, new TypeToken<List<LogDtoImpl>>() {}.getType());
    }

    /*for crop logs mapping*/
    public CropMonitoringDetails toCropLogEntity(CropLogDtoImpl cropLogDto) {
        return modelMapper.map(cropLogDto, CropMonitoringDetails.class);
    }
    public CropLogDtoImpl toCropLogDto(CropMonitoringDetails cropMonitoringDetails) {
        return modelMapper.map(cropMonitoringDetails, CropLogDtoImpl.class);
    }
    public List<CropLogDtoImpl> toAllCropLogs(List<CropMonitoringDetails> cropMonitoringDetails) {
        return modelMapper.map(cropMonitoringDetails, new TypeToken<List<CropLogDtoImpl>>() {}.getType());
    }

    /*for field logs mapping*/
    public FieldMonitoringDetails toFieldLogEntity(FieldLogDtoImpl fieldLogDto) {
        return modelMapper.map(fieldLogDto, FieldMonitoringDetails.class);
    }
    public FieldLogDtoImpl toFieldLogDto(FieldMonitoringDetails fieldMonitoringDetails) {
        return modelMapper.map(fieldMonitoringDetails, FieldLogDtoImpl.class);
    }
    public List<FieldLogDtoImpl> toAllFieldLogs(List<FieldMonitoringDetails> fieldMonitoringDetails) {
        return modelMapper.map(fieldMonitoringDetails, new TypeToken<List<FieldLogDtoImpl>>() {}.getType());
    }

    /*for field logs mapping*/
    public StaffMonitoringDetails toStaffLogEntity(StaffLogDtoImpl staffLogDto) {
        return modelMapper.map(staffLogDto, StaffMonitoringDetails.class);
    }
    public StaffLogDtoImpl toStaffLogDto(StaffMonitoringDetails staffMonitoringDetails) {
        return modelMapper.map(staffMonitoringDetails, StaffLogDtoImpl.class);
    }
    public List<StaffLogDtoImpl> toAllStaffLogs(List<StaffMonitoringDetails> staffMonitoringDetails) {
        return modelMapper.map(staffMonitoringDetails, new TypeToken<List<StaffLogDtoImpl>>() {}.getType());
    }
}
