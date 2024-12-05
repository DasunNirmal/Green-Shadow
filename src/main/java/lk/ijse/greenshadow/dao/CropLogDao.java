package lk.ijse.greenshadow.dao;

import lk.ijse.greenshadow.dto.impl.CropLogDtoImpl;
import lk.ijse.greenshadow.entity.impl.CropMonitoringDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CropLogDao  extends JpaRepository<CropMonitoringDetails, String> {
    @Query("SELECT new lk.ijse.greenshadow.dto.impl.CropLogDtoImpl(cmd.details_id, c.crop_code, ml.log_code, cmd.details, cmd.crop_name) " +
            "FROM CropMonitoringDetails cmd " +
            "JOIN cmd.crops c " +
            "JOIN cmd.monitoringLog ml")
    List<CropLogDtoImpl> findAllCropLogs();

}
