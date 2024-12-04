package lk.ijse.greenshadow.dao;

import lk.ijse.greenshadow.entity.impl.StaffMonitoringDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffLogDao extends JpaRepository<StaffMonitoringDetails, String> {
}
