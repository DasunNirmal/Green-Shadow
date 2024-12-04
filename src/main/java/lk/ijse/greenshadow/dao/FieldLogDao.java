package lk.ijse.greenshadow.dao;

import lk.ijse.greenshadow.entity.impl.FieldMonitoringDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldLogDao  extends JpaRepository<FieldMonitoringDetails, String> {
}
