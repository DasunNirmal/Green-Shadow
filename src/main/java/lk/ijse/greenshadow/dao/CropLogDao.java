package lk.ijse.greenshadow.dao;

import lk.ijse.greenshadow.entity.impl.CropMonitoringDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropLogDao  extends JpaRepository<CropMonitoringDetails, String> {
}
